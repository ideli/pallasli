package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class ShortValue extends Value {
	private short value;

	public ShortValue(short value) {
		this.value = value;
	}

	public short getShort() {
		return value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
