package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class ByteValue extends Value {
	private byte value;

	public ByteValue(byte value) {
		this.value = value;
	}

	@Override
	public byte getByte() {
		return value;
	}

	@Override
	public String getString() {
		return String.valueOf(value);
	}
}
