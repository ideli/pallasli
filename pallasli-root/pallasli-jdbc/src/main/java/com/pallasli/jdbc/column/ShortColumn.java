package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

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
