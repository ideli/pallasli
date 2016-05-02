package com.pallasli.study.spring.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.util.Log4jConfigListener;

@Order(1)
public class CommonInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext)
			throws ServletException {

		// Log4jConfigListener
		servletContext.setInitParameter("log4jConfigLocation",
				"classpath:log4j.properties");
		servletContext.addListener(Log4jConfigListener.class);

		// // OpenSessionInViewFilter
		// OpenSessionInViewFilter hibernateSessionInViewFilter = new
		// OpenSessionInViewFilter();
		// FilterRegistration.Dynamic filterRegistration = servletContext
		// .addFilter("hibernateFilter", hibernateSessionInViewFilter);
		// filterRegistration.addMappingForUrlPatterns(EnumSet.of(
		// DispatcherType.REQUEST, DispatcherType.FORWARD,
		// DispatcherType.INCLUDE), false, "/");

		// DemoServlet
		DemoServlet demoServlet = new DemoServlet();
		ServletRegistration.Dynamic dynamic = servletContext.addServlet(
				"demoServlet", demoServlet);
		dynamic.setLoadOnStartup(2);
		dynamic.addMapping("/demo_servlet");

	}

}
