package com.shineyue.htmldesign.config;

import org.springframework.boot.SpringApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shineyue.htmldesign.interceptor.TimeBasedAccessInterceptor;

public class WebAppConfig extends WebMvcConfigurerAdapter {
	public static void main(String[] args) {
		SpringApplication.run(WebAppConfig.class, args);
	}

	/**
	 * 配置拦截器
	 * 
	 * @author lance
	 * @param registry
	 */
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TimeBasedAccessInterceptor())
				.addPathPatterns("/*.do");
	}
}
