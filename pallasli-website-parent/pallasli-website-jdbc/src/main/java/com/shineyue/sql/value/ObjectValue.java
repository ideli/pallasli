package com.shineyue.sql.value;

import com.shineyue.sql.Value;

public class ObjectValue extends Value {
	private Object value;

	public ObjectValue(Object value) {
		this.value = value;
	}

	@Override
	public Object getObject() {
		return value;
	}

	@Override
	public String getString() {
		return value.toString();
	}
}
