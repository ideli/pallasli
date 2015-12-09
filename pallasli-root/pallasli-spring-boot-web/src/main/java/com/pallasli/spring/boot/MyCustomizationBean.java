package com.pallasli.spring.boot;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.stereotype.Component;

/**
 * 
 * 也可以配置servlet容器
 * 
 * server.port=8081
 * 
 * server.address=127.0.0.1
 * 
 * server.sessionTimeout=30
 * 
 * server.contextPath=/springboot
 * 
 * @author lyt
 * 
 */
@Component
public class MyCustomizationBean implements EmbeddedServletContainerCustomizer {
	/**
	 * @param container
	 * @see org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer#customize(org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer)
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setContextPath("/springboot");
		container.setPort(9000);
		container.setSessionTimeout(30);
		// container.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,
		// "/screen/error"));
	}
}