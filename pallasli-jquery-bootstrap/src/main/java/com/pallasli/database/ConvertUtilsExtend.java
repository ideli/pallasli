package com.pallasli.database;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

public class ConvertUtilsExtend extends ConvertUtils {
	public static void init() {
		ConvertUtils.register(new Converter() {
			public Object convert(Class type, Object value) {
				if (value == null) {
					return null;
				}
				String v = (String) value;
				return v + "[C]";
			}

		}, String.class);
	}
}
