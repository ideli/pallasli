package com.pallasli.testng;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class TestSimpleNG {
	@Test
	public void testAdd() {
		String str = "TestNG is working fine";
		assertEquals("TestNG is working fine", str);
	}
}