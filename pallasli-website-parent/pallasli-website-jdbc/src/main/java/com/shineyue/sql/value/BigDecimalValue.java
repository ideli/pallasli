package com.shineyue.sql.value;

import java.math.BigDecimal;

import com.shineyue.sql.Value;

public class BigDecimalValue extends Value {
	private BigDecimal value;

	public BigDecimalValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public BigDecimal getBigDecimal() {
		return value;
	}

	public void setBigDecimal(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String getString() {
		return value.toString();
	}
}
