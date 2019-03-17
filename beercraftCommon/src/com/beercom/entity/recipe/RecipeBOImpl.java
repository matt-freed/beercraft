package com.beercom.entity.recipe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.beercom.entity.Fermentable;
import com.beercom.entity.FermentableAddition;
import com.beercom.entity.Hop;
import com.beercom.entity.HopAddition;
import com.beercom.entity.FermentableAddition.FermentableUse;
import com.beercom.entity.HopAddition.HopUse;
import com.beercom.entity.CustomizableEntity;
import com.beercom.entity.MashStep;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.MiscIngredientAddition;
import com.beercom.entity.Recipe;
import com.beercom.entity.Style;
import com.beercom.entity.User;
import com.beercom.entity.Yeast;
import com.beercom.entity.YeastAddition;
import com.beercom.entity.HopAddition.HopForm;
import com.beercom.entity.Recipe.RecipeType;
import com.beercom.entity.Yeast.YeastForm;
import com.beercom.entity.ingredient.IngredientBO;

@ApplicationScoped
public class RecipeBOImpl implements RecipeBO, Serializable{

	private static final long serialVersionUID = -6454125337014582711L;
	
	//TODO: replace with userId
	private final String USERID = "SYSTEM";
	
	@Inject
	private RecipeDAO recipeDAO;
	
	@Inject
	private IngredientBO ingredientBO;

	@Override
	public List<Recipe> getAllRecipes() {
		return recipeDAO.getAllRecipes();
	}
	
	@Override
	public List<Recipe> getRecipesForUser(User user){
		return recipeDAO.getRecipesForUser(user);
	}

	@Override
	public void saveRecipe(Recipe recipe, User user) {
		prepareRecipeForSave(recipe, user.getAuthId());
		
		recipeDAO.saveRecipe(recipe);
	}
	
	@Override
	public Recipe copyRecipe(Recipe recipe){
		List<CustomizableEntity> recipeEntities = getFlattenedRecipeGraph(recipe);
		
		recipe.setDerivedFrom(recipe.getId());
		
		for(CustomizableEntity customizableEntity: recipeEntities){
			customizableEntity.setId(null);
		}
		
		recipe.setName(StringUtils.substring(recipe.getName(), 0, 40) + "-Copy");

		return recipe;
	}
	
	/**
	 * Sets properties that we want to set on every save
	 * @param customizableEntity
	 * @param authId
	 */
	private void prepareRecipeForSave(Recipe recipe, String authId){
		
		List<CustomizableEntity> recipeEntities = getFlattenedRecipeGraph(recipe);
		
		for(CustomizableEntity customizableEntity: recipeEntities){
			prepareEntityForSave(customizableEntity, authId);
		}
	}
	
	/**
	 * Sets certain properties on a specific entity for a particular user
	 * @param customizableEntity
	 * @param authId
	 */
	private void prepareEntityForSave(CustomizableEntity customizableEntity, String authId){
		customizableEntity.setMaintId(authId);
		customizableEntity.setOwner(authId);
		customizableEntity.setIsBuiltIn(false);
	}

	@Override
	public Recipe newRecipe() {
		Recipe recipe = new Recipe();
		recipe.setCreatedDate(new Date());
		
		recipe.setBatchSizeGallons(5.0);
		recipe.setType(RecipeType.ALLGRAIN);
		
		return recipe;
	}

	@Override
	public void deleteRecipe(Recipe recipe, User user) {
		
		recipeDAO.deleteRecipe(recipe);
	}

	@Override
	public Recipe searchRecipe(Long id) {
		return recipeDAO.searchRecipe(id);
	}

	@Override
	public List<Style> getAllStyles() {
		return recipeDAO.getAllStyles();
	}

	@Override
	public void saveStyle(Style style) {
		recipeDAO.saveStyle(style);	
	}

	@Override
	public Style newStyle() {
		Style style = new Style();
		return style;
	}

	@Override
	public void deleteStyle(Style style) {
		recipeDAO.deleteStyle(style);
	}
	
	@Override
	public Style searchStyle(Long id){
		return recipeDAO.searchStyle(id);
	}
	
	public RecipeDAO getRecipeDAO() {
		return recipeDAO;
	}

	public void setRecipeDAO(RecipeDAO recipeDAO) {
		this.recipeDAO = recipeDAO;
	}

	@Override
	public HopAddition newHopAdditionForRecipe(Recipe recipe, Hop hopType) {
		HopAddition hopAdd = new HopAddition();
		hopAdd.setWeightOz(1.0);
		hopAdd.setBoilTimeMin(60);
		hopAdd.setForm(HopForm.PELLET);
		hopAdd.setUse(HopUse.BOIL);
		
		Hop userHop = ingredientBO.newUserHop(USERID, hopType);
		hopAdd.setHop(userHop);
		
		recipe.getHopAdditions().add(hopAdd);
		
		return hopAdd;
	}

	@Override
	public FermentableAddition newFermentableAdditionForRecipe(Recipe recipe,
			Fermentable fermentableType) {
		FermentableAddition fermAdd = new FermentableAddition();
		fermAdd.setMass(1.0);
		fermAdd.setUse(FermentableUse.MASH);
		
		Fermentable userFerm = ingredientBO.newUserFermentable(USERID, fermentableType);
		fermAdd.setFermentable(userFerm);
		
		fermAdd.setRecipe(recipe);
		recipe.getFermentableAdditions().add(fermAdd);
		
		return fermAdd;
	}

	@Override
	public YeastAddition newYeastAdditionForRecipe(Recipe recipe,
			Yeast yeastType) {
		YeastAddition yeastAdd = new YeastAddition();

		Yeast userYeast = ingredientBO.newUserYeast(USERID,  yeastType);
		yeastAdd.setYeast(userYeast);
		
		if(yeastType.getForm().equals(YeastForm.DRY)){
			yeastAdd.setAmount("1 pkg");
		}
		else{
			yeastAdd.setAmount("1 vial");
		}
		yeastAdd.setAddToSecondary(false);
		yeastAdd.setTimesCultured(0);
		
		yeastAdd.setRecipe(recipe);
		recipe.getYeastAdditions().add(yeastAdd);
		
		return yeastAdd;
	}

	@Override
	public MiscIngredientAddition newMiscAdditionForRecipe(Recipe recipe,
			MiscIngredient miscType) {
		MiscIngredientAddition misc= new MiscIngredientAddition();
		
		MiscIngredient userMisc = ingredientBO.newUserMiscIngredient(USERID, miscType);
		misc.setMiscIngredient(userMisc);
		
		misc.setRecipe(recipe);
		recipe.getMiscAdditions().add(misc);
		
		return misc;
	}

	@Override
	public void removeHopAddition(Recipe recipe, HopAddition hopAdd) {
		recipe.getHopAdditions().remove(hopAdd);
		hopAdd.setRecipe(null);
	}

	@Override
	public void removeFermentableAddition(Recipe recipe,
			FermentableAddition fermentableAdd) {
		recipe.getFermentableAdditions().remove(fermentableAdd);
		fermentableAdd.setRecipe(null);
	}

	@Override
	public void removeYeastAddition(Recipe recipe, YeastAddition yeastAdd) {
		recipe.getYeastAdditions().remove(yeastAdd);
		yeastAdd.setRecipe(null);
	}

	@Override
	public void removeMiscAddition(Recipe recipe, MiscIngredientAddition miscAdd) {
		recipe.getMiscAdditions().remove(miscAdd);
		miscAdd.setRecipe(null);
	}
	
	/**
	 * Generates a flattened list of all descendants of the Recipe that are 
	 * customizable entities. Note this method must be maintained as entities are added
	 * to recipe (in contrast to the reflection method)
	 * @param recipe
	 * @return
	 */
	@Override
	public List<CustomizableEntity> getFlattenedRecipeGraph(Recipe recipe){
		List<CustomizableEntity> entityList = new ArrayList<>();

		entityList.add(recipe);
			
		//Fermentation Process
		if(recipe.getFermentation() != null){
			entityList.add(recipe.getFermentation());
		}
		
		//Batch log
		if(recipe.getBatch() != null){
			entityList.add(recipe.getBatch());
		}
			
		//Mash Process
		if(recipe.getMashProcess() != null){
			entityList.add(recipe.getMashProcess());

			//Mash Steps
			if(recipe.getMashProcess().getMashSteps() != null){
				for(MashStep step : recipe.getMashProcess().getMashSteps()){
					entityList.add(step);
				}
			}
		}
		
		//Fermentable additions
		if(recipe.getFermentableAdditions() != null){
			for(FermentableAddition fermAdd : recipe.getFermentableAdditions()){
				entityList.add(fermAdd);
				
				if(fermAdd.getFermentable() != null){
					entityList.add(fermAdd.getFermentable());
				}
			}
		}

		//Hop additions
		if(recipe.getHopAdditions() != null){
			for(HopAddition hopAdd : recipe.getHopAdditions()){
				entityList.add(hopAdd);
				
				if(hopAdd.getHop() != null){
					entityList.add(hopAdd.getHop());
				}
			}
		}
		
		//Yeast additions
		if(recipe.getYeastAdditions() != null){
			for(YeastAddition yeastAdd : recipe.getYeastAdditions()){
				entityList.add(yeastAdd);
				
				if(yeastAdd.getYeast() != null){
					entityList.add(yeastAdd.getYeast());
				}
			}
		}
		//MiscIngredient additions
		if(recipe.getMiscAdditions() != null){
			for(MiscIngredientAddition miscAdd : recipe.getMiscAdditions()){
				entityList.add(miscAdd);
				
				if(miscAdd.getMiscIngredient() != null){
					entityList.add(miscAdd.getMiscIngredient());
				}
			}
		}
		
		return entityList;
	}
}
