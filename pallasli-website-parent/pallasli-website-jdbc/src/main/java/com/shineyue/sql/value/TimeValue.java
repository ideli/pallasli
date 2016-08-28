package com.shineyue.sql.value;

import java.sql.Time;

import com.shineyue.sql.Value;

public class TimeValue extends Value {
	private Time value;

	public TimeValue(Time value) {
		this.value = value;
	}

	public TimeValue(java.util.Date value) {
		this.value = (value == null ? null : (new java.sql.Time(value.getTime())));
	}

	@Override
	public Time getTime() {
		return value;
	}

	@Override
	public String getString() {
		return (value == null ? "" : value.toString());
	}
}
