package com.pallasli.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class UnMarshalUtil {

	/**
	 * 将json字符串转化为指定类型对象
	 * 
	 * @param <E>
	 * @param jsonData
	 * @param cls
	 * @return
	 */
	public static <E extends Object> E unMarshal(String jsonData, Class<E> cls) {
		E rtn = null;

		Map<String, Method> map = new HashMap<String, Method>();
		Method[] methods = cls.getDeclaredMethods();
		for (Method m : methods) {
			map.put(m.getName().toLowerCase(), m);
		}
		try {
			// 特殊字符处理
			// jsonData = StringUtils.replaceComma(jsonData);

			if (!jsonData.startsWith("{") || !jsonData.endsWith("}")) {
				return rtn;
			}
			jsonData = jsonData.substring(1, jsonData.length() - 2);
			String[] keyAndValue = jsonData.split(",");

			rtn = cls.newInstance();
			for (String KVS : keyAndValue) {
				int indexOfSeparater = KVS.indexOf(":");
				if (indexOfSeparater == -1) {
					continue;
				}
				String k = KVS.substring(0, indexOfSeparater);
				String v = KVS.substring(indexOfSeparater + 1);
				k = StringUtils.removeQuotationBeginAndEnd(k);
				v = StringUtils.removeQuotationBeginAndEnd(v);
				// Method method = BasicUnit.class.getDeclaredMethod(
				// StringUtils.addPreSet(k), String.class);
				// method.invoke(rtn, v);
				Method method = map.get(StringUtils.addPreSet(k).toLowerCase());
				if (method != null) {
					Type[] types = method.getGenericParameterTypes();
					if (types.length == 1) {

						if (types[0].equals(String.class)) {
							method.invoke(rtn, new Object[] { v });
						}
						if (types[0].equals(Integer.TYPE) || types[0].toString().equals("class java.lang.Integer")) {
							method.invoke(rtn, new Object[] { new Integer(v.replaceAll("\\.\\d{1,}", "")) });
						}
						if (types[0].equals(Long.TYPE) || types[0].toString().equals("class java.lang.Long")) {
							method.invoke(rtn, new Object[] { new Long(v.replaceAll("\\.\\d{1,}", "")) });
						}
						if (types[0].equals(Double.TYPE) || types[0].toString().equals("class java.lang.Double")) {
							method.invoke(rtn, new Object[] { new Double(v) });
						} else {
							// System.out.println(types[0]);
						}
					}
				}
			}

		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return rtn;
	}

}
