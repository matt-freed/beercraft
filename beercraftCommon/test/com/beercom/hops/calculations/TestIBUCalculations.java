package com.beercom.hops.calculations;

import org.junit.Before;
import org.junit.Test;

import com.beercraft.ejb.calculation.RagerMethod;
import com.beercraft.ejb.calculation.TinsethMethod;

public class TestIBUCalculations {

	protected RagerMethod rager;
	protected TinsethMethod tinseth;
	
	@Before
	public void setup(){
		rager = new RagerMethod();
		tinseth = new TinsethMethod();
	}
	
	@Test
	public void TestRager(){
		
		double utilization = RagerMethod.utilization(5);
		double utilization2 = RagerMethod.utilization(10);
		double utilization3 = RagerMethod.utilization(15);
		double utilization4 = RagerMethod.utilization(35);
		double utilization5 = RagerMethod.utilization(45);
		
	}
	
	@Test
	public void testCalculation(){
		//Sty golding
		double ibu1 = rager.calculateIbu(28.3, .0525, 
				18.93, 1.088, 30);
		
		double ibu2 = tinseth.calculateIbu(28.3, .0525, 
				18.93, 1.088, 30);
		
	}
	
}
