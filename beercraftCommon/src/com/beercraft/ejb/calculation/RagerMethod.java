package com.beercraft.ejb.calculation;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;


/**
 * Implementation of Rager's method for calculating IBU's.  
 * Generally believed to be optimistic calculations
 * 
 * Source: http://www.realbeer.com/hops/FAQ.html Norm Pyle's Hops
 *
 */
@Alternative
@ApplicationScoped
public class RagerMethod implements IBUCalculator, Serializable{

	private static final long serialVersionUID = -2502516199540342045L;

	/**
	 * For a given hop amount and acid, determine the bitterness in ibu's it 
	 * will add to the boil
	 * @param gramsOfHops
	 * @param alpha (AAU)
	 * @param volume (in liters)
	 * @param gravity
	 * @param minutes
	 * @return the ibu's added to the boil
	 */
	@Override
	public double calculateIbu(double gramsOfHops, double alpha, double volume, double gravity, double minutes){
		double ibu = (gramsOfHops * utilization(minutes) * alpha * 1000) / (volume * (1 + gravityAdjust(gravity)));
		return ibu;
	}
	
	/**
	 * For gravities over 1.050, there is a gravity factor
	 * @param boilGravity
	 * @return the gravity factor
	 */
	public static double gravityAdjust(double boilGravity){
		double gravityFactor = 0;
		if(boilGravity > 1.050){
			gravityFactor = (boilGravity - 1.050)/.2;
		}
		return gravityFactor;
	}
	
	/**
	 * Rager's table to predict the utilization based
	 * on the boil time
	 * @param minutes
	 * @return the utilization percentage
	 */
	public static double utilization(double minutes){
		double utilization = 18.11 + 13.86 * Math.tanh((minutes - 31.32)/18.27);
		return utilization / 100;
	}
}
