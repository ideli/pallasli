package com.shineyue.sql.value;

import java.sql.Timestamp;

import com.shineyue.sql.Value;

public class TimestampValue extends Value {
	private Timestamp value;

	public TimestampValue(Timestamp value) {
		this.value = value;
	}

	public TimestampValue(java.util.Date value) {
		this.value = (value == null ? null : (new java.sql.Timestamp(value.getTime())));
	}

	@Override
	public Timestamp getTimestamp() {
		return value;
	}

	@Override
	public String getString() {
		return (value == null ? "" : value.toString());
	}
}
