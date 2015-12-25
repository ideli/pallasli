package com.pallasli.runners;

import static org.junit.Assert.*;
 

import org.junit.Test;

public class Junit4Test {
	@Test
	public void test() {
		boolean actual = true;
		assertEquals(true, actual);
	}
	
	@Test(expected=ArithmeticException.class)
	public void testThrowIoEception(){
 
		int a=1/0;
		a=a--;
	}
	

	@Test(timeout=100)
	public void testTimeout(){
		System.out.println(1);
		for(int i=0;i<100;i++){
			 
			System.out.println("i="+i);
			
		}
	}
}
