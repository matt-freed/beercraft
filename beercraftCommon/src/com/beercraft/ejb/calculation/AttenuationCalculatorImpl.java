package com.beercraft.ejb.calculation;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AttenuationCalculatorImpl implements AttenuationCalculator {

	@Override
	public double calculateFg(double og, double attenuation) {
		return (og - 1) * (1 - attenuation) + 1;
	}

	@Override
	public double calculateAbw(double og, double fg) {
		return  ((og - fg) / 1.05 ) / fg;
	}

	@Override
	public double calculateAbvBasic(double og, double fg) {
		return Math.abs((og - fg) * 131.25);
	}

	@Override
	public double calculateAbvAlternate(double og, double fg) {
		return (76.08 * (og - fg) / (1.775 - og)) * (fg / .794);
	}

	/**
	 * Returns the apparent attenuation (which is the apparent drop in gu)
	 * not the actual attenuation
	 */
	@Override
	public double calculateAttenuation(double og, double fg) {
		return (og - fg) / (og - 1);
	}

	@Override
	public double calculateBitternessRatio(double ibu, double og) {
		double gu = (og - 1) * 1000;

		if(gu > 0){
			return ibu / gu;
		}
		else{
			return 0;
		}
	}

}
