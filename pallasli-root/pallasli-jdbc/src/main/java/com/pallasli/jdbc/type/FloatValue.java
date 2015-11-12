package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class FloatValue extends Value {
	private float value;

	public FloatValue(float value) {
		this.value = value;
	}

	public float getFloat() {
		return value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
