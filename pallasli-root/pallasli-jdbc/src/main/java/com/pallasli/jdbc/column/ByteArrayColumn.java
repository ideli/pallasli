package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

public class ByteArrayColumn extends Column {
    private byte[] value;

    public ByteArrayColumn(String name, byte[] value) {
        super(name);
        this.value = value;
    }

    public byte[] getBytes() {
        return value;
    }

    public String getString() {
        return new String(value);
    }
}
