package com.beercom.calculations.fermentables;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.beercom.calculation.FermentableCalculator;
import com.beercom.calculation.FermentableCalculatorImpl;


public class TestFermentableCalculations {

	protected FermentableCalculator calculator;
	
	@Before
	public void setup(){
		calculator = new FermentableCalculatorImpl();
	}
	
	@Test
	public void testGetSG(){
		double weight = 3.0;
		double ppg = 34;
		double efficiency = .7;
		double volume = 5.5;
		
		double result = calculator.calculatePoints(weight, ppg, 1, volume) +
				calculator.calculatePoints(1.2, 36, 1, 5.5) +
				calculator.calculatePoints(.5, 33, efficiency, 5.5);
		
		double sg = calculator.ppgToSg(result);
		
		assertEquals(1.0285, sg, .01);
	}
	
	@Test
	public void testBelgian(){
		
		double result = calculator.calculatePoints(13, 36, .79, 5) +
				calculator.calculatePoints(1.6, 37, .8, 5) +
				calculator.calculatePoints(1.6, 36, .78, 5.5);
		
		double sg = calculator.ppgToSg(result);
		
		assertEquals(1.088, sg, .01);
	}
}
