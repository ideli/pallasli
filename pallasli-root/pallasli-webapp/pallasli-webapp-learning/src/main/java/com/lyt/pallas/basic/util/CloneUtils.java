package com.lyt.pallas.basic.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.lyt.pallas.basic.mapping.object.convert.ClassCovertToMap;
import com.pallasli.utils.StringUtils;

@SuppressWarnings("rawtypes")
public class CloneUtils {
	public static Object cloneAllToBecovered(Object covering, Object beCovered) {

		Class coveringClass = covering.getClass();
		Class beCoveredClass = beCovered.getClass();
		Map<String, Method> coveringMap = ClassCovertToMap
				.getMethodMap(coveringClass);
		Map<String, Method> beCoveredMap = ClassCovertToMap
				.getMethodMap(beCoveredClass);
		Map<String, Type> coveringFieldMap = ClassCovertToMap
				.getFieldTypeMap(coveringClass);
		Map<String, Type> beCoveredFieldMap = ClassCovertToMap
				.getFieldTypeMap(beCoveredClass);
		Set<String> beCoveredSet = beCoveredMap.keySet();
		Iterator<String> it = beCoveredSet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.startsWith("get")) {
				String fieldName = StringUtils.removePreGetOrSet(key);
				Type coveringType = coveringFieldMap.get(fieldName);
				Type beCoveredType = coveringFieldMap.get(fieldName);
				if (coveringType != null && coveringType.equals(beCoveredType)) {

					String setName = StringUtils.addPreSet(fieldName);
					Method m = beCoveredMap.get(setName);
					if (m != null) {
						try {
							Object value = coveringMap.get(key).invoke(
									covering, new Object[] {});
							m.invoke(beCovered, value);
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return beCovered;

	}

	public static Object cloneAllToBecoveredWhereNull(Object covering,
			Object beCovered) {

		Class coveringClass = covering.getClass();
		Class beCoveredClass = beCovered.getClass();
		Map<String, Method> coveringMap = ClassCovertToMap
				.getMethodMap(coveringClass);
		Map<String, Method> beCoveredMap = ClassCovertToMap
				.getMethodMap(beCoveredClass);
		Map<String, Type> coveringFieldMap = ClassCovertToMap
				.getFieldTypeMap(coveringClass);
		Map<String, Type> beCoveredFieldMap = ClassCovertToMap
				.getFieldTypeMap(beCoveredClass);
		Set<String> beCoveredSet = beCoveredMap.keySet();
		Iterator<String> it = beCoveredSet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.startsWith("get")) {
				String fieldName = StringUtils.removePreGetOrSet(key);
				Type coveringType = coveringFieldMap.get(fieldName);
				Type beCoveredType = coveringFieldMap.get(fieldName);
				if (coveringType != null && coveringType.equals(beCoveredType)) {

					String setName = StringUtils.addPreSet(fieldName);
					Method m = beCoveredMap.get(setName);
					if (m != null) {
						try {
							Object obj = beCoveredMap.get(key).invoke(
									beCovered, new Object[] {});
							if (obj == null) {
								m.invoke(beCovered, coveringMap.get(key)
										.invoke(covering, new Object[] {}));
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return beCovered;

	}

	public static Object cloneNotNullToBecovered(Object covering,
			Object beCovered) {

		Class coveringClass = covering.getClass();
		Class beCoveredClass = beCovered.getClass();
		Map<String, Method> coveringMap = ClassCovertToMap
				.getMethodMap(coveringClass);
		Map<String, Method> beCoveredMap = ClassCovertToMap
				.getMethodMap(beCoveredClass);
		Map<String, Type> coveringFieldMap = ClassCovertToMap
				.getFieldTypeMap(coveringClass);
		Map<String, Type> beCoveredFieldMap = ClassCovertToMap
				.getFieldTypeMap(beCoveredClass);
		Set<String> beCoveredSet = beCoveredMap.keySet();
		Iterator<String> it = beCoveredSet.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (key.startsWith("get")) {
				String fieldName = StringUtils.removePreGetOrSet(key);
				Type coveringType = coveringFieldMap.get(fieldName);
				Type beCoveredType = coveringFieldMap.get(fieldName);
				if (coveringType != null && coveringType.equals(beCoveredType)) {

					String setName = StringUtils.addPreSet(fieldName);
					Method m = beCoveredMap.get(setName);
					if (m != null) {
						try {
							Object value = coveringMap.get(key).invoke(
									covering, new Object[] {});
							if (value != null) {
								m.invoke(beCovered, value);
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}

					}
				}
			}
		}
		return beCovered;

	}
}
