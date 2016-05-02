package com.pallali.study.guava;

import java.awt.Color;

public class Test1<E> {
	public static <E> Test2<E> test() {
		return new Test2<E>();
	}

	public static void main(String[] args) {
		Test1<Color> t = Test1.<Color> test().test();
	}
}

class Test2<E> {
	public <E> Test1<E> test() {
		return new Test1<E>();
	}

}