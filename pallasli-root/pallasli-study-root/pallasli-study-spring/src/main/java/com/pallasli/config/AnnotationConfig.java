package com.pallasli.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.pallasli.scanclass.ScanTestBean;

@Configuration
@ComponentScan(basePackages = { "com.pallasli.bean", "com.pallasli.control",
		"com.pallasli.service.impl", "com.pallasli.repository.impl" }, basePackageClasses = { ScanTestBean.class })
@Import(ImportConfig1.class)
public class AnnotationConfig {

}
