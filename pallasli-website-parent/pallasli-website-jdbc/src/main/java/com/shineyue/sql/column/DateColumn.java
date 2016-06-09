package com.shineyue.sql.column;

import java.sql.*;

import com.shineyue.sql.Column;


public class DateColumn
	extends Column {
  private Date value;

  public DateColumn(String name, Date value) {
	super(name);
	this.value = value;
  }

  public DateColumn(String name, java.util.Date value) {
	super(name);
	this.value = new java.sql.Date(value.getTime());
  }

  public Date getDate() {
	return value;
  }

  public String getString() {
	return (value == null) ? "" : value.toString();
  }
}
