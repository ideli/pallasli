package com.pallasli.jdbc.type;

import com.pallasli.jdbc.Value;

public class DateValue extends Value {
	private java.sql.Date value;

	public DateValue(java.sql.Date value) {
		this.value = value;
	}

	public DateValue(java.util.Date value) {
		this.value = (value == null ? null
				: (new java.sql.Date(value.getTime())));
	}

	public java.sql.Date getDate() {
		return value;
	}

	public String getString() {
		return (value == null ? "" : value.toString());
	}
}
