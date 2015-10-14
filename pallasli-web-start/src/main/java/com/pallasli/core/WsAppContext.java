package com.pallasli.core;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pallasli.core.constant.PropertiesConstants;
import com.pallasli.core.constant.SystemConstants;
import com.pallasli.core.properties.PropertiesFactory;
import com.pallasli.core.properties.PropertiesHelper;

public class WsAppContext {

	private static Logger log = LogManager.getLogger(WsAppContext.class);
	private static ApplicationContext applicationContext;

	static {
		try {
			initApplicationContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化ApplicationContext对象
	 * 
	 * @throws Exception
	 */
	private static void initApplicationContext() throws Exception {
		PropertiesHelper pHelper = PropertiesFactory
				.getPropertiesHelper(PropertiesConstants.SERVER);
		String forceLoad = pHelper.getValue("forceLoad",
				SystemConstants.FORCELOAD_N);
		try {
			if (forceLoad.equalsIgnoreCase(SystemConstants.FORCELOAD_N)) {
				log.info("系统正在初始化Spring...");
			}
			applicationContext = new ClassPathXmlApplicationContext(
					new String[] { "application.xml" });
			if (forceLoad.equalsIgnoreCase(SystemConstants.FORCELOAD_N)) {
				log.info("Spring初始化成功,SpringBean已经被实例化。");
			}
		} catch (Exception e) {
			log.error("Spring初始化失败.");
			log.error("初始化Spring发生错误,请仔细检查您的配置文件喔!\n" + e.getMessage());
			System.exit(0);
			throw e;
		}
	}

	/**
	 * 返回ApplicationContext对象
	 * 
	 * @return ApplicationContext 返回的ApplicationContext实例
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取一个SpringBean服务
	 * 
	 * @param pBeanId
	 *            Spring配置文件名中配置的SpringID号
	 * @return Object 返回的SpringBean实例
	 */
	public static Object getSpringBean(String pBeanId) {
		Object springBean = null;
		try {
			springBean = applicationContext.getBean(pBeanId);
		} catch (NoSuchBeanDefinitionException e) {
			log.error("Spring配置文件中没有匹配到ID号为:[" + pBeanId
					+ "]的SpringBean组件,请检查!");
			log.error(e.getMessage());
		}
		return springBean;
	}

}
