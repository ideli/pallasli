package com.pallasli.javastudy;

import java.util.Locale;

import org.junit.Test;

public class StringUtils {
	@Test
	public void compareEmp() {
		String str = "Hello World";
		String anotherString = "hello world";
		Object objStr = str;

		System.out.println(str.compareTo(anotherString));
		System.out.println(str.compareToIgnoreCase(anotherString));
		System.out.println(str.compareTo(objStr.toString()));
	}

	@Test
	public void searchlastString() {
		String strOrig = "Hello world ,Hello Reader";
		int lastIndex = strOrig.lastIndexOf("Hello");
		if (lastIndex == -1) {
			System.out.println("Hello not found");
		} else {
			System.out.println("Last occurrence of Hello is at index "
					+ lastIndex);
		}
	}

	@Test
	public void removeCharAt() {
		String s = "this is Java";
		s = s.substring(0, 3) + s.substring(3 + 1);
	}

	@Test
	public void replaceEmp() {
		String str = "Hello World";
		System.out.println(str.replace('H', 'W'));
		System.out.println(str.replaceFirst("He", "Wa"));
		System.out.println(str.replaceAll("He", "Ha"));
	}

	@Test
	public void reverseExample() {
		String string = "abcdef";
		String reverse = new StringBuffer(string).reverse().toString();
		System.out.println("String before reverse:" + string);
		System.out.println("String after reverse:" + reverse);
	}

	@Test
	public void searchStringEmp() {
		String strOrig = "Hello readers";
		int intIndex = strOrig.indexOf("Hello");
		if (intIndex == -1) {
			System.out.println("Hello not found");
		} else {
			System.out.println("Found Hello at index " + intIndex);
		}
	}

	@Test
	public void splitEmp() {
		String str = "jan-feb-march";
		String[] temp;
		String delimeter = "-";
		temp = str.split(delimeter);
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
			System.out.println("");
			str = "jan.feb.march";
			delimeter = "\\.";
			temp = str.split(delimeter);
		}
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
			System.out.println("");
			temp = str.split(delimeter, 2);
			for (int j = 0; j < temp.length; j++) {
				System.out.println(temp[i]);
			}
		}
	}

	@Test
	public void stringToUpperCaseEmp() {
		String str = "string abc touppercase ";
		String strUpper = str.toUpperCase();
		System.out.println("Original String: " + str);
		System.out.println("String changed to upper case: " + strUpper);
	}

	@Test
	public void StringRegionMatch() {
		String first_str = "Welcome to Microsoft";
		String second_str = "I work with Microsoft";
		boolean match = first_str.regionMatches(11, second_str, 12, 9);
		System.out.println("first_str[11 -19] == " + "second_str[12 - 21]:-"
				+ match);
	}

	@Test
	public void StringComparePerformance() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			String s1 = "hello";
			String s2 = "hello";
			s1 = s2;
			s2 = s1;
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for creation" + " of String literals : "
				+ (endTime - startTime) + " milli seconds");
		long startTime1 = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			String s3 = new String("hello");
			String s4 = new String("hello");
			s3 = s4;
			s4 = s3;
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Time taken for creation" + " of String objects : "
				+ (endTime1 - startTime1) + " milli seconds");
	}

	@Test
	public void StringOptimization() {
		// 优化字符串创建
		String variables[] = new String[50000];
		for (int i = 0; i < 50000; i++) {
			variables[i] = "s" + i;
		}
		long startTime0 = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			variables[i] = "hello";
		}
		long endTime0 = System.currentTimeMillis();
		System.out.println("Creation time" + " of String literals : "
				+ (endTime0 - startTime0) + " ms");
		long startTime1 = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			variables[i] = new String("hello");
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Creation time of"
				+ " String objects with 'new' key word : "
				+ (endTime1 - startTime1) + " ms");
		long startTime2 = System.currentTimeMillis();
		for (int i = 0; i < 50000; i++) {
			variables[i] = new String("hello");
			variables[i] = variables[i].intern();
		}
		long endTime2 = System.currentTimeMillis();
		System.out.println("Creation time of"
				+ " String objects with intern(): " + (endTime2 - startTime2)
				+ " ms");
	}

	@Test
	public void StringFormat() {
		double e = Math.E;
		System.out.format("%f%n", e);
		System.out.format(Locale.GERMANY, "%-10.4f%n%n", e);
	}

	@Test
	public void StringConcatenate() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String result = "This is" + "testing the" + "difference"
					+ "between" + "String" + "and" + "StringBuffer";
			System.out.println(result);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time taken for string"
				+ "concatenation using + operator : " + (endTime - startTime)
				+ " ms");
		long startTime1 = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			StringBuffer result = new StringBuffer();
			result.append("This is");
			result.append("testing the");
			result.append("difference");
			result.append("between");
			result.append("String");
			result.append("and");
			result.append("StringBuffer");
		}
		long endTime1 = System.currentTimeMillis();
		System.out.println("Time taken for String concatenation"
				+ "using StringBuffer : " + (endTime1 - startTime1) + " ms");
	}

	@Test
	public void StringUniCode() {
		String test_string = "Welcome to TutorialsPoint";
		System.out.println("String under test is = " + test_string);
		System.out.println("Unicode code point at"
				+ " position 5 in the string is = "
				+ test_string.codePointBefore(5));
	}

}
