package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class DoubleValue extends Value {
	private double value;

	public DoubleValue(double value) {
		this.value = value;
	}

	public double getDouble() {
		return value;
	}

	public void setDouble(double value) {
		this.value = value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
