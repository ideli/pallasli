package com.pallasli.study.spring.config.security;

import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@Order(2)
public class WebAppSecurityInitializer extends
		AbstractSecurityWebApplicationInitializer {
	// servletContext.addListener("org.springframework.security.web.session.HttpSessionEventPublisher");
	// session监听器
	@Override
	protected boolean enableHttpSessionEventPublisher() {
		return true;
	}
}