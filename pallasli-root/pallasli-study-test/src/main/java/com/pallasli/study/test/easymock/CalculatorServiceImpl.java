package com.pallasli.study.test.easymock;
public class CalculatorServiceImpl implements CalculatorService {

	public double add(double input1, double input2) {
		System.out.println("dddddddddd");
		return input1 + input2;
	}

	public double subtract(double input1, double input2) {
		// TODO Auto-generated method stub
		return input1 - input2;
	}

	public double multiply(double input1, double input2) {
		// TODO Auto-generated method stub
		return input1 * input2;
	}

	public double divide(double input1, double input2) {
		// TODO Auto-generated method stub
		return input1 / input2;
	}

}
