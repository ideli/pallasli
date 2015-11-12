package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class BooleanValue extends Value {
	private boolean value;

	public BooleanValue(boolean value) {
		this.value = value;
	}

	public boolean getBoolean() {
		return value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
