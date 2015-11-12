package com.pallasli.utils;

import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberUtils {
	private static DecimalFormat numberFormat = new DecimalFormat();

	public static double round(double value, int precision) {
		if (precision == 0) {
			return Math.round(value);
		} else if (precision > 0) {
			long l = (long) Math.pow(10, precision);
			return Math.round(value * l) / (double) l;
		}
		return 0D;
	}

	public static boolean isValidInteger(String numberString, int min, int max) {
		Integer validInteger = null;
		try {
			Number aNumber = numberFormat.parse(numberString);
			int anInt = aNumber.intValue();
			if (anInt >= min && anInt <= max) {
				validInteger = new Integer(anInt);
			}
		} catch (ParseException e) {
			// Ignore and return null
		}
		return validInteger != null;
	}

	public static Number toNumber(String numString, String numFormatPattern)
			throws ParseException {
		Number number = null;
		if (numFormatPattern == null) {
			numFormatPattern = "######.##";
		}
		synchronized (numberFormat) {
			numberFormat.applyPattern(numFormatPattern);
			number = numberFormat.parse(numString);
		}
		return number;
	}
}
