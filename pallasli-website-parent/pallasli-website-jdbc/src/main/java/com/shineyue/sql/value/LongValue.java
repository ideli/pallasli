package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class LongValue extends Value {
	private long value;

	public LongValue(long value) {
		this.value = value;
	}

	@Override
	public long getLong() {
		return value;
	}

	@Override
	public String getString() {
		return String.valueOf(value);
	}
}
