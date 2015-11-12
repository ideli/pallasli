package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

public class DoubleColumn extends Column {
    private double value;

    public DoubleColumn(String name, double value) {
        super(name);
        this.value = value;
    }

    public double getDouble() {
        return value;
    }


	public void setDouble(double value) {
		this.value = value;
	}

    public String getString() {
        return String.valueOf(value);
    }
}
