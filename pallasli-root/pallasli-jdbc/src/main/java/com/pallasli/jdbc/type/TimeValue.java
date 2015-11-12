package com.pallasli.jdbc.type;

import java.sql.Time;

import com.pallasli.jdbc.Value;

public class TimeValue extends Value {
	private Time value;

	public TimeValue(Time value) {
		this.value = value;
	}

	public TimeValue(java.util.Date value) {
		this.value = (value == null ? null
				: (new java.sql.Time(value.getTime())));
	}

	public Time getTime() {
		return value;
	}

	public String getString() {
		return (value == null ? "" : value.toString());
	}
}
