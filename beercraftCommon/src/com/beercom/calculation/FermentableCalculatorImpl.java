package com.beercom.calculation;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;

/**
 * http://homebrew.stackexchange.com/questions/1434/wiki-how-do-you-calculate-original-gravity
 *
 */
@ApplicationScoped
public class FermentableCalculatorImpl implements FermentableCalculator, Serializable{

	private static final long serialVersionUID = -8451757643393987579L;

	/**
	 * Calculates the added points of a single fermentable addition.
	 * This represents how much the gravity will increase
	 * @param weight (in pounds)
	 * @param ppg (points per pound per gallon)
	 * @param efficiency (mash efficiency)
	 * @param volume (gallons)
	 * @return the sg
	 */
	@Override
	public double calculatePoints(double weight, double ppg, double efficiency, double gallonsOfWater){
		double result =  (calculateMaxPotential(weight, ppg) * efficiency) / gallonsOfWater;
		
		return result;
	}
	
	/**
	 * Calculates the theoretical max potential (total amount, not volume based)
	 * of a single fermentable addition.
	 * This represents the amount of sugar
	 * @param weight (in pounds)
	 * @param ppg (points per pound per gallon)
	 * @param efficiency (mash efficiency)
	 * @param volume (gallons)
	 * @return the sg
	 */
	@Override
	public double calculateMaxPotential(double weight, double ppg){
		return  weight * ppg;
	}
	
	/**
	 * Determine the brew house efficiency of the batch (sugars lost during mashing and left behind in trub, etc)
	 * @param measuredOg
	 * @param maxPotential
	 * @param batchSizeGallons
	 * @return
	 */
	@Override
	public double calculateEfficiency(double measuredOg, double maxPotential, double batchSizeGallons){
		 double potential = sgToPpg(measuredOg) * batchSizeGallons;
		 
		 return potential / maxPotential;
	 }
	
	/**
	 * Converts ppg to potential/standard gravity
	 * @param ppg
	 * @return
	 */
	@Override
	public double ppgToSg(double ppg){
		return ppg / 1000 + 1;
	}
	
	public double sgToPpg(double sg){
		return (sg - 1) * 1000;
	}

	@Override
	public double calculateMCU(double color, double weight, double gallonsOfWater) {
		return (color * weight) / gallonsOfWater;
	}

	@Override
	public double calculateSRM(double mcu) {
		return 1.4922 * (Math.pow(mcu, .6859));
	}

}
