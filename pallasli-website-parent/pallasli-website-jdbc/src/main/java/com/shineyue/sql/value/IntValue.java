package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class IntValue extends Value {
	private int value;

	public IntValue(int value) {
		this.value = value;
	}

	@Override
	public int getInt() {
		return value;
	}

	@Override
	public String getString() {
		return String.valueOf(value);
	}
}
