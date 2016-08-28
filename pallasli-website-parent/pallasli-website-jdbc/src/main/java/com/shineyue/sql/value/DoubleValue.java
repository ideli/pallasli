package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class DoubleValue extends Value {
	private double value;

	public DoubleValue(double value) {
		this.value = value;
	}

	@Override
	public double getDouble() {
		return value;
	}

	@Override
	public void setDouble(double value) {
		this.value = value;
	}

	@Override
	public String getString() {
		return String.valueOf(value);
	}
}
