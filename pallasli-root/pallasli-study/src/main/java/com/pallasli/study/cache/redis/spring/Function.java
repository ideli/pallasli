package com.pallasli.study.cache.redis.spring;

public interface Function<T, E> {
    public T callback(E e);
}
