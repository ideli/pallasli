package com.pallasli.jdbc.column;

import com.pallasli.jdbc.Column;

public class ObjectColumn extends Column {
    private Object value;

    public ObjectColumn(String name, Object value) {
        super(name);
        this.value = value;
    }

    public Object getObject() {
        return value;
    }

    public String getString() {
        return value.toString();
    }
}
