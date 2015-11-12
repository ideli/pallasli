package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

public class IntColumn extends Column {
    private int value;

    public IntColumn(String name, int value) {
        super(name);
        this.value = value;
    }

    public int getInt() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }
}
