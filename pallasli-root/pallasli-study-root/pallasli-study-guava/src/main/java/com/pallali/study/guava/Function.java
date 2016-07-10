package com.pallali.study.guava;

/**
 * 其功能就是将输入类型转换为输出类型
 */
public interface Function<F, T> {
	T apply(F input);
}
