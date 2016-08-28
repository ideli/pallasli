package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class ShortValue extends Value {
	private short value;

	public ShortValue(short value) {
		this.value = value;
	}

	@Override
	public short getShort() {
		return value;
	}

	@Override
	public String getString() {
		return String.valueOf(value);
	}
}
