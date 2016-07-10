package com.pallali.study.guava;

public interface Predicate<T> {
	boolean apply(T input); // 不同于Function.apply, 该apply用于过滤对象
}
