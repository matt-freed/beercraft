package com.beercom.facade;

import com.beercom.entity.Batch;
import com.beercom.entity.Recipe;

public interface CalculationFacade {
	
	/**
	 * Calculates all parameters for a recipe
	 * @param recipe
	 * @return
	 */
	public Recipe calculateRecipe(Recipe recipe);
	
	/**
	 * Calculates all parameters for a batch
	 * (error, efficiency, etc)
	 * @param recipe
	 * @return
	 */
	public Batch calculateBatch(Recipe recipe);
	

	/**
	 * For a given recipe, calculate the ibu's and sets the property in the recipe
	 * @param recipe
	 * @return
	 */
	public double calculateIbu(Recipe recipe);
	
	/**
	 * For a given recipe, calculate the preboil volume
	 * and sg
	 * @param recipe
	 */
	public void calculateBoilOff(Recipe recipe);
	
	/**
	 * For a given recipe, calculate the estimated original gravity
	 * @param recipe
	 * @return
	 */
	public double calculateSG(Recipe recipe);
	
	/**
	 * For a given list of fermentable additions, estimate the final color
	 * @param fermentables
	 * @param volume
	 * @return
	 */
	public double calculateColor(Recipe recipe);
	
	/**
	 * From the original gravity, estimate the final gravity based on the yeast
	 * attenuation. Note that the actual fg may vary greatly depending on 
	 * the fermentability of the wort
	 * @param recipe
	 * @return
	 */
	public double calculateFG(Recipe recipe);
	
	/**
	 * For a fg and an og, estimate the abv
	 * @param recipe
	 * @return
	 */
	public double calculateAbv(Recipe recipe);
	
	/**
	 * Calculate the ratio of ibu to gravity units
	 * @param recipe
	 * @return
	 */
	public double calculateBitternessRatio(Recipe recipe);
}
