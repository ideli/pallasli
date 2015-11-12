package com.pallasli.jdbc.column;

import java.sql.*;

import com.pallasli.jdbc.Column;


public class TimestampColumn extends Column {
    private Timestamp value;

    public TimestampColumn(String name, Timestamp value) {
        super(name);
        this.value = value;
    }

	public TimestampColumn(String name, java.util.Date value) {
	  super(name);
	  this.value = new Timestamp(value.getTime());
	}

    public Timestamp getTimestamp() {
        return value;
    }

    public String getString() {
        return (value == null) ? "" : value.toString();
    }
}
