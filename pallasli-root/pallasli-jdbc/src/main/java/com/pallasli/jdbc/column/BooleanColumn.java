package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

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
