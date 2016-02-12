package com.palllasli.utils;

import org.junit.Test;

import com.pallasli.utils.ZHConverterUtils;

public class ZHConverterUtilsTest {

	public static final String[] propertyFiles = new String[2];

	static {
		propertyFiles[ZHConverterUtils.TRADITIONAL] = "zh2Hant.properties";
		propertyFiles[ZHConverterUtils.SIMPLIFIED] = "zh2Hans.properties";
	}

	@Test
	public void convert()

	{
		String a = new ZHConverterUtils(propertyFiles[ZHConverterUtils.TRADITIONAL]).convert("应该");
		System.out.println(a);
		a = new ZHConverterUtils(propertyFiles[ZHConverterUtils.SIMPLIFIED]).convert("應該");
		System.out.println(a);
	}
}
