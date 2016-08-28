package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class FloatValue extends Value {
	private float value;

	public FloatValue(float value) {
		this.value = value;
	}

	@Override
	public float getFloat() {
		return value;
	}

	@Override
	public String getString() {
		return String.valueOf(value);
	}
}
