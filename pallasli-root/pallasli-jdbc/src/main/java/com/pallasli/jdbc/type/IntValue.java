package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class IntValue extends Value {
	private int value;

	public IntValue(int value) {
		this.value = value;
	}

	public int getInt() {
		return value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
