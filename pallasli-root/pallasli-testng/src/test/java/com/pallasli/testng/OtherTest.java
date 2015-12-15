package com.pallasli.testng;

import org.testng.annotations.Test;

public class OtherTest {
	@Test(expectedExceptions = ArithmeticException.class)
	public void testExpectedExceptions() {
		System.out.println("Inside testPrintMessage()");
	}

	@Test(enabled = false)
	public void testIgnore() {
		System.out.println("Inside testPrintMessage()");
	}

	@Test(groups = { "init" })
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
	}

	@Test(dependsOnMethods = { "testPrintMessage" })
	public void testDependsOnMethods() {
		System.out.println("Inside testSalutationMessage()");
	}

	@Test(dependsOnGroups = { "init.*" })
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
	}

	@Test(groups = { "init" })
	public void initEnvironmentTest() {
		System.out.println("This is initEnvironmentTest");
	}
}
