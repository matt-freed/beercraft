package com.beercom.facade.entity;

import java.util.List;

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

public interface RecipeFacade {

	public List<Recipe> getAllRecipes();
	
	public List<Recipe> getRecipesForUser(User user);
	
	public void saveRecipe(Recipe recipe, User user);
	
	public Recipe newRecipe();
	
	public void deleteRecipe(Recipe recipe, User user);
	
	public Recipe searchRecipe(Long id);
	
	public Recipe copyRecipe(Recipe recipe);
	
	
	public HopAddition newHopAdditionForRecipe(Recipe recipe, Hop hopType); 
	
	public FermentableAddition newFermentableAdditionForRecipe(Recipe recipe, Fermentable fermentableType);
	
	public YeastAddition newYeastAdditionForRecipe(Recipe recipe, Yeast yeastType);
	
	public MiscIngredientAddition newMiscAdditionForRecipe(Recipe recipe, MiscIngredient miscType);
	
	public void removeHopAddition(Recipe recipe, HopAddition hopAdd);
	
	public void removeFermentableAddition(Recipe recipe, FermentableAddition fermentableAdd);
	
	public void removeYeastAddition(Recipe recipe, YeastAddition yeastAdd);
	
	public void removeMiscAddition(Recipe recipe, MiscIngredientAddition miscAdd);

	
	public List<Style> getAllStyles();
	
	public void saveStyle(Style style);
	
	public Style newStyle();
	
	public void deleteStyle(Style style);
	
	public Style searchStyle(Long id);
	
}
