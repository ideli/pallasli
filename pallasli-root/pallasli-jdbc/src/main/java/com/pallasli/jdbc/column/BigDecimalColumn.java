package com.pallasli.jdbc.column;

import java.math.*;

import com.pallasli.jdbc.Column;


public class BigDecimalColumn
	extends Column {
  private BigDecimal value;

  public BigDecimalColumn(String name, BigDecimal value) {
	super(name);
	//bNull = (value == null) ? true : false;
	this.value = value;
  }

  public BigDecimal getBigDecimal() {
	return value;
  }

  public void setBigDecimal(BigDecimal value) {
	this.value = value;
  }

  public String getString() {
	return value.toString();
  }
}
