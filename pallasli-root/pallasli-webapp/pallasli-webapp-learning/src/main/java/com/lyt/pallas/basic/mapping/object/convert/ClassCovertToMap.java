package com.lyt.pallas.basic.mapping.object.convert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ClassCovertToMap {
	public static Map<String, Method> getMethodMap(Class<? extends Object> cls) {
		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			map.put(m.getName(), m);
		}
		return map;
	}

	public static Map<String, Type> getFieldTypeMap(Class<? extends Object> cls) {
		Map<String, Type> map = new HashMap<String, Type>();
		Field[] fileds = cls.getDeclaredFields();
		for (Field f : fileds) {
			map.put(f.getName(), f.getGenericType());
		}
		return map;
	}

	public static Map<String, Object> getFieldValueMap(
			Class<? extends Object> cls) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fileds = cls.getFields();
		for (Field f : fileds) {
			String key = f.getName();
			Object value;
			try {
				value = f.get(f.getName());
				map.put(key, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static Map<String, String> getStringFieldValueMap(
			Class<? extends Object> cls) {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fileds = cls.getFields();
		for (Field f : fileds) {
			if (String.class.equals(f.getGenericType())) {
				String key = f.getName();
				Object value;
				try {
					value = f.get(f.getName());
					if (value != null) {
						map.put(key, value.toString());
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		return map;
	}
}
