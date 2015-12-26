package com.pallasli.runners;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value=Parameterized.class)
public class ParameterizedTest {

	private double firstV;
	private double sencondV;
	private double resultV;
	
	@Parameters
	public static Collection<Double[]> getParam(){
		return Arrays.asList(new Double[][]{
				{2.0,1.0,1.0},
				{4.5,1.5,3.0},
		});
	}
	public ParameterizedTest(double firstV,
	  double sencondV,
	  double resultV){
		this.firstV=firstV;
		this.sencondV=sencondV;
		this.resultV=resultV;
	}
	@Test
	public void test(){

 		assertEquals(this.resultV, this.firstV-this.sencondV,0);
	
	}
	
}
