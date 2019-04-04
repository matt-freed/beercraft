package com.beercraft.ejb.calculation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Contains various methods for working with boil 
 * calculations
 */
@ApplicationScoped
public class BoilCalculator {
	
	@Inject
	private FermentableCalculator fermentableCalculator;
	
	/**
	 * From the target volume and water losses due to evaporation and left behind in trub,
	 * estimate the required pre boil volume
	 * @param trubLossG
	 * @param boilLossGPH
	 * @param timeH
	 * @param targetVolG
	 * @return
	 */
	public double calculatePreBoilFromTarget(double trubLossG, double boilLossGPH, double timeH, double targetVolG){
		double lossVolG = timeH * boilLossGPH + trubLossG;
		
		return targetVolG + lossVolG;
	}
	
	/**
	 * Given a fixed amount of sugars, estimate what the og should be before boiling
	 * @param og
	 * @param preBoilVolG
	 * @param postBoilVolG
	 * @return
	 */
	public double calculatePreBoilSgFromOG(double og, double preBoilVolG, double postBoilVolG){
		double preBoilPpg = fermentableCalculator.sgToPpg(og);
		
		return fermentableCalculator.ppgToSg(preBoilPpg * postBoilVolG / preBoilVolG);
	}

}
