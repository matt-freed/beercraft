package com.beercom.calculation;

public interface FermentableCalculator {
	
	/**
	 * Calculates the added points of a single fermentable addition
	 * @param weight (in pounds)
	 * @param ppg (points per pound per gallon)
	 * @param efficiency (brewhouse efficiency)
	 * @param volume (gallons)
	 * @return the sg
	 */
	public double calculatePoints(double weight, double ppg, double efficiency, double gallonsOfWater);
	
	/**
	 * Calculates the theoretical max potential (total amount, not volume based)
	 * of a single fermentable addition
	 * @param weight (in pounds)
	 * @param ppg (points per pound per gallon)
	 * @param efficiency (mash efficiency)
	 * @param volume (gallons)
	 * @return the sg
	 */
	public double calculateMaxPotential(double weight, double ppg);
	
	/**
	 * Determine the brew house efficiency of the batch (sugars lost during mashing and left behind in trub, etc)
	 * @param measuredOg
	 * @param maxPotential
	 * @param batchSizeGallons
	 * @return
	 */
	public double calculateEfficiency(double measuredOg, double maxPotential, double batchSizeGallons);
	
	/**
	 * Converts ppg to potential/standard gravity
	 * @param ppg
	 * @return
	 */
	public double ppgToSg(double ppg);
	
	/**
	 * Converts sg to ppg
	 * @param ppg
	 * @return
	 */
	public double sgToPpg(double sg);
	
	/**
	 * Calculate the Malt Color Unit for a single grain
	 * 
	 * http://brewwiki.com/index.php/Estimating_Color
	 * @param color (in MCU?)
	 * @param weight (in lbs)
	 * @param volume (gallons)
	 * @return
	 */
	public double calculateMCU(double color, double weight, double gallonsOfWater);
	
	/**
	 * From a given Standard Reference Method value (aggregate of all grains) 
	 * convert to SRM color, which is more accurate for darker colors
	 * Uses Morey Equation
	 * http://brewgr.com/calculations/srm-beer-color
	 * @param MCU
	 * @return
	 */
	public double calculateSRM(double mcu);

}
