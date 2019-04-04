package com.beercraft.web.resource;

import java.security.GeneralSecurityException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.beercraft.ejb.RecipeCalculator;
import com.beercraft.ejb.RecipeService;
import com.beercraft.ejb.entity.FermentableAddition;
import com.beercraft.ejb.entity.HopAddition;
import com.beercraft.ejb.entity.MashStep;
import com.beercraft.ejb.entity.MiscIngredientAddition;
import com.beercraft.ejb.entity.Recipe;
import com.beercraft.ejb.entity.User;
import com.beercraft.ejb.entity.YeastAddition;
import com.beercraft.web.util.GoogleAuthUtil;

/**
 * Rest services for managing recipes
 * Note: Request scope annotation allows CDI injection to work with Jersey resource class
 */
@RequestScoped
@Path("/recipe")
public class RecipeResource {
	
	private static final Logger log = LogManager.getLogger(RecipeResource.class);

	@Inject
	private RecipeService recipeService;
	
	@Inject
	private RecipeCalculator recipeCalculator;
	
	@Inject
	private GoogleAuthUtil authUtil;

	/**
	 * Calculates recipe 
	 */
	@POST
	@Path("/calculate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Recipe calculateRecipe(@NotNull @Valid Recipe recipe){

		recipeCalculator.calculateRecipe(recipe);
		
		//Changing recipe parameters will cause batch expectations to change
		recipeCalculator.calculateBatch(recipe);
		
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
			recipeService.saveRecipe(recipe, user);
		}
		catch(SecurityException e){
			log.warn("Unauthorized user", e);
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
		
		Recipe copiedRecipe = recipeService.copyRecipe(recipe);
		
		calculateRecipe(recipe);
		
		formatRecipeForSave(recipe, user);
		
		recipeService.saveRecipe(copiedRecipe, user);
		
		return copiedRecipe;
		
	}
	
	@GET
	@Path("/recipes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Recipe> getRecipes(@HeaderParam("Authorization") String tokenId){
		
		User user = authenticate(tokenId);
		
		List<Recipe> recipes = recipeService.getRecipesForUser(user);
		
		return recipes;
	}
	
	@POST
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteRecipe(@NotNull Recipe recipe, @HeaderParam("Authorization") String tokenId){
		
		User user = authenticate(tokenId);
		
		formatRecipeForSave(recipe, user);
		
		try{
			recipeService.deleteRecipe(recipe, user);
		}
		catch(SecurityException e){
			log.warn("Unauthorized user", e);
			throw new NotAuthorizedException("User does not have the rights to save this recipe");
		}
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
			log.warn("Unauthorized user", e);
			throw new NotAuthorizedException("User could not be authorized");
		}
		return user;
	}
	
}
