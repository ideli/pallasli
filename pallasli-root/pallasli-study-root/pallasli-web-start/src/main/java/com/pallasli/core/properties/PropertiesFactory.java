package com.pallasli.core.properties;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.pallasli.core.constant.PropertiesConstants;

public class PropertiesFactory {
	private static Logger log = LogManager.getLogger(PropertiesFactory.class);
	/**
	 * 属性文件实例容器
	 */
	private static Map<String, Object> container = new HashMap<String, Object>();

	static {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();

		if (classLoader == null) {
			classLoader = PropertiesFactory.class.getClassLoader();
		}
		// 加载属性文件server.properties
		try {
			InputStream is = classLoader
					.getResourceAsStream("server.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesConstants.SERVER, ph);
		} catch (Exception e1) {
			log.error("加载属性文件server.properties出错!");
		}
		// 加载属性文件app.properties
		try {
			InputStream is = classLoader.getResourceAsStream("app.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesConstants.APP, ph);
		} catch (Exception e1) {
			log.error("加载属性文件app.properties出错!");
		}
		// 加载属性文件jetty.server.properties
		try {
			InputStream is = classLoader
					.getResourceAsStream("jetty.server.properties");
			PropertiesHelper ph = new PropertiesHelper(is);
			container.put(PropertiesConstants.JETTYSERVER, ph);
		} catch (Exception e1) {
			log.error("加载属性文件jetty.server.properties出错!");
		}
	}

	/**
	 * 获取属性文件实例
	 * 
	 * @param pFile
	 *            文件类型
	 * @return 返回属性文件实例
	 */
	public static PropertiesHelper getPropertiesHelper(String pFile) {
		PropertiesHelper ph = (PropertiesHelper) container.get(pFile);
		return ph;
	}
}
