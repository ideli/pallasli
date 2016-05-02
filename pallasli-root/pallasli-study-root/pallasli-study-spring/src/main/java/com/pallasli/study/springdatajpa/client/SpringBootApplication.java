package com.pallasli.study.springdatajpa.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableAutoConfiguration
@EnableScheduling
public class SpringBootApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(new Object[] { UserController.class }, args);
	}
}
