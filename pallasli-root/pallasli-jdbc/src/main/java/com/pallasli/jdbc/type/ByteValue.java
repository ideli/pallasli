package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class ByteValue extends Value {
	private byte value;

	public ByteValue(byte value) {
		this.value = value;
	}

	public byte getByte() {
		return value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
