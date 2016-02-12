package com.pallasli.study.apachecommons.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

import com.pallasli.study.apachecommons.bean.Person;

public class ConvertUtilsExtend extends ConvertUtils {
	public static void init() {
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
				if (value == null) {
					return null;
				}
				String v = (String) value;
				return v + "[C]";
			}

		}, String.class);
	}

	// 将object转化成int型
	public static int intConvert(Object object) {
		String str = object == null ? null : object.toString();
		Object obj = ConvertUtils.convert(str, Integer.class);
		Integer i = (Integer) obj;
		return i;
	}

	// 将任意的Object转换成string
	public static String stringConvert(Object object) {
		Object obj = ConvertUtils.convert(object);
		String string = (String) obj;
		return string;
	}

	public static boolean booleanConvert(Object object) {
		String str = object == null ? null : object.toString();
		Object obj = ConvertUtils.convert(str, Boolean.class);
		boolean b = (Boolean) obj;
		return b;
	}

	public static void test2() {
		Map map = new HashMap();
		map.put("name", "xiazdong");
		map.put("age", "20");
		map.put("birth", "2010-10-10");
		ConvertUtils.register(new Converter() {
			@Override
			public Object convert(@SuppressWarnings("rawtypes") Class type, Object value) {
				if (value == null) {
					return null;
				}
				String v = (String) value;
				return v + "[C]";
			}

		}, String.class);
		ConvertUtils.register(new DateLocaleConverter(), Date.class);
		Person p = new Person();
		try {
			BeanUtils.populate(p, map);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(p.getName());
		System.out.println(p.getAge());
		System.out.println(p.getBirth().toLocaleString());
	}

	public static void main(String[] arg) {
		System.out.println(intConvert("123"));
		System.out.println(stringConvert(456.2));
		System.out.println(booleanConvert("true"));
		test2();
	}
}
