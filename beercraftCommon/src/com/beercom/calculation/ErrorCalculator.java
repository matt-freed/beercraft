package com.beercom.calculation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Compares measured values to expected
 */
@ApplicationScoped
public class ErrorCalculator {
	
	@Inject
	private FermentableCalculator fermentableCalculator;

	/**
	 * Experimental error of gravity units as a percentage
	 * @param measured
	 * @param expected
	 * @return
	 */
	public Double calculateGravityError(double measured, double expected){
		double measuredGU = fermentableCalculator.sgToPpg(measured);
		double expectedGU = fermentableCalculator.sgToPpg(expected);
		return calculateError(measuredGU, expectedGU);
	}
	
	
	/**
	 * Experimental error as a percentage
	 * @param measured
	 * @param expected
	 * @return
	 */
	public Double calculateError(double measured, double expected){
		if(expected != 0){
			return Math.abs((measured - expected) / expected) * 100;
		}
		else{
			return null;
		}
	}
}
