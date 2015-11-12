package com.pallasli.utils;

public class ArrayUtils {
	public static int arrayIndexOf(String[] arr, String value) {
		if (arr == null || value == null || arr.length <= 0
				|| value.length() <= 0) {
			return -1;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals(value)) {
				return i;
			}
		}
		return -1;
	}

	public static boolean Contains(String[] array, String value) {
		boolean isIncluded = false;

		if (array == null || value == null) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (value.equals(array[i])) {
				isIncluded = true;
				break;
			}
		}
		return isIncluded;
	}

	public static boolean Contains(int[] array, int value) {
		boolean isIncluded = false;

		if (array == null) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				isIncluded = true;
				break;
			}
		}
		return isIncluded;
	}

	public static final String arrayToString(byte[] bytes) {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buff.append(bytes[i] + " ");
		}
		return buff.toString();
	}
}
