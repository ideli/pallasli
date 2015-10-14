package com.atwasoft.framework.container.entity;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {
    public abstract String toString();
    public abstract boolean equals(Object o);
    public abstract int hashCode();
}
