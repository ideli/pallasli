package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class StringValue extends Value {
	private String value;

	public StringValue(String value) {
		this.value = value;
	}

	@Override
	public String getString() {
		return (value == null ? "" : value);
	}
}
