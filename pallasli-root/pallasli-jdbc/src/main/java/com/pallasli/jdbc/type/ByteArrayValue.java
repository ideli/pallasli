package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class ByteArrayValue extends Value {
	private byte[] value;

	public ByteArrayValue(byte[] value) {
		this.value = value;
	}

	public byte[] getBytes() {
		return value;
	}

	public String getString() {
		return new String(value);
	}

	public int getBytesCount() {
		return this.value.length;
	}
}
