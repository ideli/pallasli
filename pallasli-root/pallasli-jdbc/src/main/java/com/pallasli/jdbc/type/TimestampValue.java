package com.pallasli.jdbc.type;

import java.sql.Timestamp;

import com.pallasli.jdbc.Value;

public class TimestampValue extends Value {
	private Timestamp value;

	public TimestampValue(Timestamp value) {
		this.value = value;
	}

	public TimestampValue(java.util.Date value) {
		this.value = (value == null ? null : (new java.sql.Timestamp(
				value.getTime())));
	}

	public Timestamp getTimestamp() {
		return value;
	}

	public String getString() {
		return (value == null ? "" : value.toString());
	}
}
