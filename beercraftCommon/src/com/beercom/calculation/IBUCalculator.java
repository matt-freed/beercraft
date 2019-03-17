package com.beercom.calculation;

public interface IBUCalculator {
	
	/**
	 * For a given hop amount and acid, determine the bitterness in ibu's it 
	 * will add to the boil
	 * @param gramsOfHops (grams)
	 * @param alpha acid percent (%)
	 * @param litersOfWater (liters)
	 * @param gravity (SG)
	 * @param minutes
	 * @return the ibu's added to the boil
	 */
	public double calculateIbu(double gramsOfHops, double alpha, double litersOfWater, double gravity, double minutes);
}
