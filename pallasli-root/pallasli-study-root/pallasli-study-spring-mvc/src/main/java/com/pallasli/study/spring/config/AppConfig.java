package com.pallasli.study.spring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = "web.function", excludeFilters = { @ComponentScan.Filter(type = FilterType.ANNOTATION, value = { Controller.class }) })
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({ CachingConfig.class, DaoConfig.class })
public class AppConfig {
}