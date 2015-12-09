package com.palllasli.utils;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.pallasli.utils.RandomUtils;

@RunWith(value = Parameterized.class)
public class RandomUtilsTest {

	private final int length;

	public RandomUtilsTest(int length) {
		this.length = length;
	}

	@Parameters
	public static Collection<Integer[]> getTestParameters() {
		return Arrays.asList(new Integer[][] {

		{ 13 }, { 15 }, { 18 } });
	}

	@Test
	public void testGetRandomString() {
		Set<String> randoms = new HashSet<String>();
		for (int i = 0; i < 100000; i++) {
			String random = RandomUtils.getRandomString(length);

			assertFalse(randoms.contains(random));
			randoms.add(random);
		}
	}

	@Test
	public void generateCaptchaText() {
		// Set<String> randoms = new HashSet<String>();
		for (int i = 0; i < 100000; i++) {
			RandomUtils.generateCaptchaText("tmp");

		}
	}

	// @Test
	// public void generateCaptchaImage() {
	// Set<String> randoms = new HashSet<String>();
	// RandomUtils.generateCaptchaText("tmp");
	// byte[] bs = RandomUtils.generateCaptchaImage("tmp");
	//
	// }
}
