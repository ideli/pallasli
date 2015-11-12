package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class ObjectValue extends Value {
	private Object value;

	public ObjectValue(Object value) {
		this.value = value;
	}

	public Object getObject() {
		return value;
	}

	public String getString() {
		return value.toString();
	}
}
