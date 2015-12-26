package com.pallasli.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SystempathAppContext implements AppContext {

	public ApplicationContext getContext() {
		ApplicationContext appContext = new FileSystemXmlApplicationContext(
				"K:/pallasli-spring-study/src/main/resources/applicationContext.xml");

		return appContext;
	}

}
