package com.shineyue.sql.column;

import com.shineyue.sql.Column;


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
