package com.shineyue.sql.column;

import com.shineyue.sql.Column;


public class BytesColumn extends Column {
    private byte[] value;

    public BytesColumn(String name, byte[] value) {
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
