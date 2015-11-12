package com.pallasli.jdbc.type;

import java.math.BigDecimal;

import com.pallasli.jdbc.Value;

public class BigDecimalValue extends Value {
	private BigDecimal value;

	public BigDecimalValue(BigDecimal value) {
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
