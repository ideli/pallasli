package com.shineyue.sql.column;

import com.shineyue.sql.Column;


public class ByteColumn extends Column {
    private byte value;

    public ByteColumn(String name, byte value) {
        super(name);
        this.value = value;
    }

    public byte getByte() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }
}
