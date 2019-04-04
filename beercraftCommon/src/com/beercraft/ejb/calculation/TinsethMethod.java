package com.beercraft.ejb.calculation;
import javax.enterprise.context.ApplicationScoped;

/**
 * Implementation of Tinseth's Method. 
 * Thought to be generally accurate, calculated values are usually
 * between Rager and Garetz
 * 
 * Source: http://www.realbeer.com/hops/FAQ.html Norm Pyle's Hops
 */
@ApplicationScoped
public class TinsethMethod implements IBUCalculator{
	
	/**
	 * For a given hop amount and acid, determine the bitterness in ibu's it 
	 * will add to the boil
	 * @param gramsOfHops
	 * @param alpha (AAU)
	 * @param litersOfWater (in liters)
	 * @param gravity
	 * @param minutes
	 * @return the ibu's added to the boil
	 */
	public double calculateIbu(double gramsOfHops, double alpha, double litersOfWater,
			double gravity, double minutes) {
		//TODO: M.Freed - error checking: throw illegal argument exception
		double ibu = (alpha * utilization(gravity, minutes) * gramsOfHops * 1000 ) / litersOfWater;
		
		return ibu;
	}
	
	/**
	 * Tinseth takes into account gravity in utilization calculation
	 * @param wortGravity
	 * @param minutes
	 * @return
	 */
	public double utilization(double wortGravity, double minutes){
		double bignessFactor = 1.65 * Math.pow(.000125, wortGravity - 1);
		double boilTimeFactor = (1 - Math.exp(-.04 * minutes)) / 4.15;
		
		double utilization = bignessFactor * boilTimeFactor;

		return utilization;
	}

}
