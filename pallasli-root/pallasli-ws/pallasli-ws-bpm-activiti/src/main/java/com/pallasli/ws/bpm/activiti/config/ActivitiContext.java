package com.pallasli.ws.bpm.activiti.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ActivitiContext {

	private static volatile ClassPathXmlApplicationContext context;

	static {

		context = new ClassPathXmlApplicationContext("activitiContext.xml");

		context.registerShutdownHook();

	}

	public static ApplicationContext getContext() {
		return context;
	}

}
