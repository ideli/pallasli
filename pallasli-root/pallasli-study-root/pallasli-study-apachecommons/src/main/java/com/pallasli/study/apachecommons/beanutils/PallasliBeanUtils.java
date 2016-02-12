package com.pallasli.study.apachecommons.beanutils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class PallasliBeanUtils {

	/**
	 * 复制对象
	 * 
	 * @param bean
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static Object cloneBean(Object bean)
			throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.cloneBean(bean);

	}

	/**
	 * 将一个对象的属性赋值给另一个对象
	 * 
	 * @param dest
	 * @param orig
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperties(Object dest, Object orig)
			throws IllegalAccessException, InvocationTargetException {

		BeanUtils.copyProperties(dest, orig);
	}

	/**
	 * 
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

		BeanUtils.copyProperty(bean, name, value);
	}

	/**
	 * 给一个对象的指定属性赋值
	 * 
	 * @param bean
	 * @param name
	 * @param value
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	public static void setProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

		BeanUtils.setProperty(bean, name, value);
	}

	/**
	 * 获取一个对象的指定属性
	 * 
	 * @param bean
	 * @param name
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static String getProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.getProperty(bean, name);

	}

	public static String[] getArrayProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.getArrayProperty(bean, name);
	}

	public static String getMappedProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.getMappedProperty(bean, name);

	}

	public static String getMappedProperty(Object bean, String name, String key)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.getMappedProperty(bean, name, key);

	}

	public static String getNestedProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.getNestedProperty(bean, name);

	}

	public static String getSimpleProperty(Object bean, String name)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		return BeanUtils.getSimpleProperty(bean, name);

	}

}
