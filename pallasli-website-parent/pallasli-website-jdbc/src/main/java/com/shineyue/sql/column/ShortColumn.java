package com.shineyue.sql.column;

import com.shineyue.sql.Column;


public class ShortColumn extends Column {
    private short value;

    public ShortColumn(String name, short value) {
        super(name);
        this.value = value;
    }

    public short getShort() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }
}
