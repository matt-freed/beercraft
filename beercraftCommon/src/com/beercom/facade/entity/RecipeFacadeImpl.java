package com.beercom.facade.entity;


import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.beercom.entity.Fermentable;
import com.beercom.entity.FermentableAddition;
import com.beercom.entity.Hop;
import com.beercom.entity.HopAddition;
import com.beercom.entity.MiscIngredient;
import com.beercom.entity.MiscIngredientAddition;
import com.beercom.entity.Recipe;
import com.beercom.entity.Style;
import com.beercom.entity.User;
import com.beercom.entity.Yeast;
import com.beercom.entity.YeastAddition;
import com.beercom.entity.recipe.RecipeBO;
import com.beercom.interceptor.Transactional;

@Dependent
@Transactional
public class RecipeFacadeImpl implements RecipeFacade, Serializable{

	private static final long serialVersionUID = 3742518577383923226L;
	
	@Inject
	private RecipeBO recipeBO;
	
	@Override
	public List<Recipe> getAllRecipes() {
		return recipeBO.getAllRecipes();
	}
	
	@Override
	public List<Recipe> getRecipesForUser(User user) {
		return recipeBO.getRecipesForUser(user);
	}

	@Override
	public Recipe newRecipe() {
		Recipe recipe = recipeBO.newRecipe();
		return recipe;
	}

	@Override
	public void deleteRecipe(Recipe recipe, User user) {
		recipeBO.deleteRecipe(recipe, user);
	}

	@Override
	public Recipe searchRecipe(Long id) {
		return recipeBO.searchRecipe(id);
	}

	@Override
	public void saveRecipe(Recipe recipe, User user) {
		recipeBO.saveRecipe(recipe, user);
	}
	
	@Override
	public Recipe copyRecipe(Recipe recipe){
		return recipeBO.copyRecipe(recipe);
	}
	
	@Override
	public HopAddition newHopAdditionForRecipe(Recipe recipe, Hop hopType) {
		return recipeBO.newHopAdditionForRecipe(recipe, hopType);
	}

	@Override
	public FermentableAddition newFermentableAdditionForRecipe(Recipe recipe,
			Fermentable fermentableType) {
		return recipeBO.newFermentableAdditionForRecipe(recipe, fermentableType);
	}

	@Override
	public YeastAddition newYeastAdditionForRecipe(Recipe recipe,
			Yeast yeastType) {
		return recipeBO.newYeastAdditionForRecipe(recipe, yeastType);
	}

	@Override
	public MiscIngredientAddition newMiscAdditionForRecipe(Recipe recipe,
			MiscIngredient miscType) {
		return recipeBO.newMiscAdditionForRecipe(recipe, miscType);
	}

	@Override
	public void removeHopAddition(Recipe recipe, HopAddition hopAdd) {
		recipeBO.removeHopAddition(recipe, hopAdd);
	}

	@Override
	public void removeFermentableAddition(Recipe recipe,
			FermentableAddition fermentableAdd) {
		recipeBO.removeFermentableAddition(recipe, fermentableAdd);
	}

	@Override
	public void removeYeastAddition(Recipe recipe, YeastAddition yeastAdd) {
		recipeBO.removeYeastAddition(recipe, yeastAdd);
	}

	@Override
	public void removeMiscAddition(Recipe recipe, MiscIngredientAddition miscAdd) {
		recipeBO.removeMiscAddition(recipe, miscAdd);
	}

	@Override
	public List<Style> getAllStyles() {
		return recipeBO.getAllStyles();
	}

	@Override
	public void saveStyle(Style style) {
		recipeBO.saveStyle(style);
	}

	@Override
	public Style newStyle() {
		return recipeBO.newStyle();
	}

	@Override
	public void deleteStyle(Style style) {
		recipeBO.deleteStyle(style);
	}
	
	@Override
	public Style searchStyle(Long id){
		return recipeBO.searchStyle(id);
	}
	
	public RecipeBO getRecipeBO() {
		return recipeBO;
	}

	public void setRecipeBO(RecipeBO recipeBO) {
		this.recipeBO = recipeBO;
	}

}
