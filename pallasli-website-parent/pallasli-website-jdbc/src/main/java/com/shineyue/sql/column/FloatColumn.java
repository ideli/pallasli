package com.shineyue.sql.column;

import com.shineyue.sql.Column;


public class FloatColumn extends Column {
    private float value;

    public FloatColumn(String name, float value) {
        super(name);
        this.value = value;
    }

    public float getFloat() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }
}
