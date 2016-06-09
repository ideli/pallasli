package com.shineyue.sql.column;

import com.shineyue.sql.Column;


public class BooleanColumn extends Column {
    private boolean value;

    public BooleanColumn(String name, boolean value) {
        super(name);
        this.value = value;
    }

    public boolean getBoolean() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }
}
