package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

public class LongColumn extends Column {
    private long value;

    public LongColumn(String name, long value) {
        super(name);
        this.value = value;
    }

    public long getLong() {
        return value;
    }

    public String getString() {
        return String.valueOf(value);
    }
}
