package com.pallasli.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class MashalUtil {
	public static <E extends Object> String marshal(List<E> list) {

		StringBuffer rtn = new StringBuffer();
		rtn.append("[");
		for (E bu : list) {
			rtn.append("{");
			Class<? extends Object> cls = bu.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				try {
					if (!"serialVersionUID".equals(fieldName)) {
						String methodName = StringUtils.addPreGet(fieldName);
						Method method;

						method = cls.getDeclaredMethod(methodName, new Class<?>[] {});
						Object value = (method.invoke(bu, new Object[] {}));
						if (value != null) {
							rtn.append(fieldName).append(":'").append(value).append("',");
						}
					}
				} catch (SecurityException e1) {
					e1.printStackTrace();
				} catch (NoSuchMethodException e1) {
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					e1.printStackTrace();
				}
			}
			rtn.deleteCharAt(rtn.length() - 1);
			rtn.append("},");
		}
		int index = rtn.lastIndexOf(",");
		if (index != -1) {
			rtn.deleteCharAt(index);
		}
		rtn.append("]");
		return rtn.toString();

	}

	public static <E extends Object> String marshal(E bu) {

		StringBuffer rtn = new StringBuffer();
		rtn.append("[");
		rtn.append("{");
		Class<? extends Object> cls = bu.getClass();
		Field[] fields = cls.getDeclaredFields();

		for (Field field : fields) {
			String fieldName = field.getName();

			String methodName = StringUtils.addPreGet(fieldName);

			Method method;

			try {
				method = cls.getDeclaredMethod(methodName, new Class<?>[] {});
				String value = (method.invoke(bu, new Object[] {})).toString();
				rtn.append(fieldName).append(":'").append(value).append("',");

			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
		}
		rtn.deleteCharAt(rtn.length() - 1);
		rtn.append("}");

		rtn.append("]");
		return rtn.toString();

	}
}
