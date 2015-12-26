package com.pallasli.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClasspathAppContext implements AppContext {

	public ApplicationContext getContext() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		return appContext;
	}
}
