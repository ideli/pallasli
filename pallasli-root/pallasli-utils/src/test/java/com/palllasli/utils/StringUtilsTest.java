package com.palllasli.utils;

import org.junit.Test;

public class StringUtilsTest {
	@Test
	public  void main( ) {
		String a = "昆仑武";
		String b = "少林寺";
		int jmlen = b.length();
		for (int i = 0; i < a.length(); i++) {
			char c = a.charAt(i);
			char d = b.charAt(i);
			c = (char) (c ^ d);
			// System.out.println(c);
			d = (char) (c ^ d);
			c = (char) (c ^ d);
			System.out.println(c);
			System.out.println(d);

		}

	}
}
