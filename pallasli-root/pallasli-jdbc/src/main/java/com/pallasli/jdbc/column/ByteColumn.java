package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

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
