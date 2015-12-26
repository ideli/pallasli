package com.pallasli.runners;

import junit.framework.TestCase;

import org.junit.internal.runners.JUnit38ClassRunner;
import org.junit.runner.RunWith;

@RunWith(value = JUnit38ClassRunner.class)
public class Junit38Test extends TestCase {
	public void test() {
		boolean actual = true;
		assertEquals(true, actual);
	}
}
