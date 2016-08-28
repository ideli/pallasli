package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class BytesValue extends Value {
	private byte[] value;

	public BytesValue(byte[] value) {
		this.value = value;
	}

	@Override
	public byte[] getBytes() {
		return value;
	}

	@Override
	public String getString() {
		return new String(value);
	}

	public int getBytesCount() {
		return this.value.length;
	}
}
