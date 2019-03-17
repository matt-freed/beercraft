package com.beercom.calculation;

public interface AttenuationCalculator {

	/**
	 * Estimates what the final gravity will be for a given attenuation.
	 * Note, this attenuation can be estimated from the yeast, but is highly dependent on
	 * the fermentability of the wort
	 * http://brewgr.com/calculations/final-gravity
	 * @param og
	 * @param attenuation
	 * @return
	 */
	public double calculateFg(double og, double attenuation);
	
	/**
	 * Calculate the alcohol by weight
	 * 
	 * http://brewgr.com/calculations/alcohol-content
	 * @param og
	 * @param fg
	 * @return
	 */
	public double calculateAbw(double og, double fg);
	
	/**
	 * Calculate the alcohol by volume using the basic formula
	 * @param og
	 * @param fg
	 * @return
	 */
	public double calculateAbvBasic(double og, double fg);
	
	/**
	 * Calculate alcohol by volume using the alternate formula.
	 * This method is thought to be more accurate at higher og's
	 * 
	 * http://www.brewersfriend.com/2011/06/16/alcohol-by-volume-calculator-updated/
	 * @param og
	 * @param fg
	 * @return
	 */
	public double calculateAbvAlternate(double og, double fg);
	
	/**
	 * Calculate what the attenuation was based on the og and fg
	 * 
	 * http://www.whitelabs.com/beer/homebrew/beginners-attenuation-and-flocculation-definitions
	 * 
	 * @param og
	 * @param fg
	 * @return
	 */
	public double calculateAttenuation(double og, double fg);
	
	/**
	 * Calculates the ratio of IBU to GU, used to determine balance
	 * 
	 * @param ibu
	 * @param og
	 * @return
	 */
	public double calculateBitternessRatio(double ibu, double og);
}
