package com.beercraft.web.resource;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.beercom.entity.FermentableAddition;
import com.beercom.entity.HopAddition;
import com.beercom.entity.MashStep;
import com.beercom.entity.MiscIngredientAddition;
import com.beercom.entity.Recipe;
import com.beercom.entity.Style;
import com.beercom.entity.User;
import com.beercom.entity.YeastAddition;
import com.beercom.facade.CalculationFacade;
import com.beercom.facade.entity.RecipeFacade;
import com.beercom.util.CDIUtil;
import com.beercraft.web.util.GoogleAuthUtil;

/**
 * Rest services for managing recipes
 * Note: Request scope annotation allows CDI injection to work with Jersey resource class
 */
@RequestScoped
@Path("/recipe")
public class RecipeResource {

	private RecipeFacade recipeFacade;
	
	private CalculationFacade calculationFacade;
	
	private GoogleAuthUtil authUtil;
	
	/**
	 * Jersey does not play nice with Weld CDI (It uses H2K
	 * when you specify the @Valid annotation).
	 * So we have to manually inject our dependencies
	 */
	@PostConstruct
	public void init(){
		recipeFacade = CDIUtil.findBean(RecipeFacade.class);
		calculationFacade = CDIUtil.findBean(CalculationFacade.class);
		authUtil = CDIUtil.findBean(GoogleAuthUtil.class);
	}

	@GET
	@Path("/styles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Style> getStyles(){
		List<Style> styles = recipeFacade.getAllStyles();
		return styles;
	}
	
	/**
	 * Calculates recipe 
	 */
	@POST
	@Path("/calculate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe calculateRecipe(@NotNull @Valid Recipe recipe){

		calculationFacade.calculateRecipe(recipe);
		
		//Changing recipe parameters will cause batch expectations to change
		calculationFacade.calculateBatch(recipe);
		
		return recipe;
	}
	
	/**
	 * Saves a recipe to the db
	 * @throws GeneralSecurityException 
	 * 
	 * @Valid annotation provides automatic Java Bean validation
	 * by annotations. A 400 Bad Request will be returned in the event of validation failure
	 */
	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe saveRecipe(@NotNull @Valid Recipe recipe, @HeaderParam("Authorization") String tokenId){
		
		User user = authenticate(tokenId);
		
		calculateRecipe(recipe);
		
		formatRecipeForSave(recipe, user);
		try{
			recipeFacade.saveRecipe(recipe, user);
		}
		catch(SecurityException e){
			throw new NotAuthorizedException("User does not have the rights to save this recipe");
		}
		
		return recipe;
	}
	
	@POST
	@Path("/copy")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe copyRecipe(@NotNull @Valid Recipe recipe, @HeaderParam("Authorization") String tokenId){
		
		User user = authenticate(tokenId);
		
		Recipe copiedRecipe = recipeFacade.copyRecipe(recipe);
		
		calculateRecipe(recipe);
		
		formatRecipeForSave(recipe, user);
		
		recipeFacade.saveRecipe(copiedRecipe, user);
		
		return copiedRecipe;
		
	}
	
	@GET
	@Path("/recipes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getRecipes(@HeaderParam("Authorization") String tokenId){
		
		User user = authenticate(tokenId);
		
		List<Recipe> recipes = recipeFacade.getRecipesForUser(user);
		
		return recipes;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteRecipe(@NotNull Recipe recipe, @HeaderParam("Authorization") String tokenId){
		
		User user = authenticate(tokenId);
		
		formatRecipeForSave(recipe, user);
		
		try{
			recipeFacade.deleteRecipe(recipe, user);
		}
		catch(SecurityException e){
			throw new NotAuthorizedException("User does not have the rights to save this recipe");
		}
		//TODO: why are we returning this?
		return recipe.getId().toString();
	}
	
	/**
	 * Adds references in many to one scenarios, these must be added
	 * on server side to avoid infinite recursion serialization
	 * @param recipe
	 * @return
	 */
	private Recipe formatRecipeForSave(Recipe recipe, User user){
		
		for(HopAddition hopAdd: recipe.getHopAdditions()){
			hopAdd.setRecipe(recipe);
		}
		for(FermentableAddition fermAdd: recipe.getFermentableAdditions()){
			fermAdd.setRecipe(recipe);
		}
		for(YeastAddition yeastAdd: recipe.getYeastAdditions()){
			yeastAdd.setRecipe(recipe);
		}
		for(MiscIngredientAddition miscAdd: recipe.getMiscAdditions()){
			miscAdd.setRecipe(recipe);
		}
		if(recipe.getMashProcess() != null){
			if(recipe.getMashProcess().getMashSteps() != null){
				for(MashStep step: recipe.getMashProcess().getMashSteps()){
					step.setMashProcess(recipe.getMashProcess());
				}
			}
		}
		if(recipe.getBatch() != null){
			recipe.getBatch().setRecipe(recipe);
		}
		return recipe;
	}
	
	/**
	 * Authenticates a user based on the token ID sent from google oauth
	 * @param tokenId
	 * @return
	 */
	private User authenticate(String tokenId){
		User user = null;
		try {
			user = authUtil.getUserInfo(tokenId);
		} catch (GeneralSecurityException e) {
			throw new NotAuthorizedException("User could not be authorized");
		}
		return user;
	}
	
}
