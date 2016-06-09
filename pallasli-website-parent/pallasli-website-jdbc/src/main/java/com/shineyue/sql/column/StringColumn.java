package com.shineyue.sql.column;

import com.shineyue.sql.Column;


public class StringColumn extends Column {
    private String value;

    public StringColumn(String name, String value) {
        super(name);
        this.value = value;
    }

    public String getString() {
        return value;
    }
}
