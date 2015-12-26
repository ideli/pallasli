package com.pallasli.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.pallasli.config.AnnotationConfig;

public class AnnotationAppContext implements AppContext {

	public ApplicationContext getContext() {
		ApplicationContext appContext = new AnnotationConfigApplicationContext(
				AnnotationConfig.class);

		return appContext;
	}

}
