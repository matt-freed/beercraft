package com.beercom.entity.recipe;

import java.util.List;

import com.beercom.entity.Recipe;
import com.beercom.entity.Style;
import com.beercom.entity.User;

public interface RecipeDAO {
	
	public List<Recipe> getAllRecipes();
	
	public List<Recipe> getRecipesForUser(User user);
	
	public void saveRecipe(Recipe recipe);
	
	public void deleteRecipe(Recipe recipe);
	
	public Recipe searchRecipe(Long id);
	
	
	public List<Style> getAllStyles();
	
	public void saveStyle(Style style);
	
	public void deleteStyle(Style style);
	
	public Style searchStyle(Long id);
	
}
