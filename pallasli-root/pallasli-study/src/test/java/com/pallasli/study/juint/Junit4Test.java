package com.pallasli.study.juint;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Junit4Test {
	@Test
	public void test() {
		boolean actual = true;
		assertEquals(true, actual);
	}

	@Before
	public void before() {
		System.out.println("before");
	}

	@After
	public void after() {
		System.out.println("after");
	}

	// @BeforeClass
	// public void beforeCLass() {
	// System.out.println("beforeCLass");
	// }

	@Test(expected = ArithmeticException.class)
	public void testThrowIoEception() {

		int a = 1 / 0;
		a = a--;
	}

	@Test(timeout = 100)
	@Ignore
	public void testTimeout() {
		System.out.println(1);
		for (int i = 0; i < 1000; i++) {

			System.out.println("i=" + i);

		}
	}
}
