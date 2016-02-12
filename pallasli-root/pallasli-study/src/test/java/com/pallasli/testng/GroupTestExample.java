package com.pallasli.testng;

import org.testng.annotations.Test;

public class GroupTestExample {
	String message = ".com";

	@Test(groups = { "functest", "checkintest" })
	public void testPrintMessage() {
		System.out.println("Inside testPrintMessage()");
		message = ".com";
	}

	@Test(groups = { "checkintest" })
	public void testSalutationMessage() {
		System.out.println("Inside testSalutationMessage()");
		message = "tutorialspoint" + ".com";
	}

	@Test(groups = { "functest" })
	public void testingExitMessage() {
		System.out.println("Inside testExitMessage()");
		message = "www." + "tutorialspoint" + ".com";
	}
}
