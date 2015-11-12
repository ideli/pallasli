package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class StringValue extends Value {
	private String value;

	public StringValue(String value) {
		this.value = value;
	}

	public String getString() {
		return (value == null ? "" : value);
	}
}
