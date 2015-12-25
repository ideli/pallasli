package com.lyt.pallas.basic.util.xml.xmlaccessframework.util;

public class StringTool {

	public static String parseFirstToUpper(String str) {
		String s = String.valueOf(str.charAt(0)).toUpperCase()
				.concat(str.substring(1));
		return s;
	}

	public static String parseFirstToLower(String str) {
		String s = String.valueOf(str.charAt(0)).toLowerCase()
				.concat(str.substring(1));
		return s;
	}

	public static String parseToGetField(String field) {
		String s = "get".concat(parseFirstToUpper(field));
		return s;
	}

	public static String parseToSetField(String field) {
		String s = "set".concat(parseFirstToUpper(field));
		return s;
	}

	public static String parseToField(String method) {
		if (method.substring(0, 3).equals("set")) {
			String s = parseFirstToUpper(method.substring(3));
			return s;
		}
		return null;
	}

}
