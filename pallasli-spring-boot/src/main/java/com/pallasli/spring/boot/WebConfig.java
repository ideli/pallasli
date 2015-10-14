package com.pallasli.spring.boot;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
	@Bean
	public FilterRegistrationBean filterRegistrationBean(MyFilter myFilter) {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(myFilter);
		filterRegistrationBean.setEnabled(true);
		filterRegistrationBean.addUrlPatterns("/bb");
		System.out.println("***");
		System.out.println("***");
		System.out.println("***");
		System.out.println("***");
		return filterRegistrationBean;
	}
}
