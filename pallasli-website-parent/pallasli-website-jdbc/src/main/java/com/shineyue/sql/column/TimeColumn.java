package com.shineyue.sql.column;

import java.sql.*;

import com.shineyue.sql.Column;


public class TimeColumn
	extends Column {
  private Time value;

  public TimeColumn(String name, Time value) {
	super(name);
	this.value = value;
  }

  public TimeColumn(String name, java.util.Date value) {
	super(name);
	this.value = new Time(value.getTime());
  }

  public Time getTime() {
	return value;
  }

  public String getString() {
	return (value == null) ? "" : value.toString();
  }
}
