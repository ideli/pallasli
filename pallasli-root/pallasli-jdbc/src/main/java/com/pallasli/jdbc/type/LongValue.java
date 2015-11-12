package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class LongValue extends Value {
	private long value;

	public LongValue(long value) {
		this.value = value;
	}

	public long getLong() {
		return value;
	}

	public String getString() {
		return String.valueOf(value);
	}
}
