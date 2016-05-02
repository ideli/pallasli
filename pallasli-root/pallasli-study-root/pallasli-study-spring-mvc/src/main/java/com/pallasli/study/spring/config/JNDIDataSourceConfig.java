package com.pallasli.study.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiObjectFactoryBean;

//@Configuration
// 加载资源文件
public class JNDIDataSourceConfig {

	@Bean
	public JndiObjectFactoryBean jndiObjectFactoryBean() {
		JndiObjectFactoryBean factory = new JndiObjectFactoryBean();
		// factory.setJndiName("java:comp/env/jdbc/demoDB");
		// //两种方式均可，spring会自动补齐
		factory.setJndiName("jdbc/demoDB");
		return factory;
	}

	@Bean(name = "dataSource")
	public DataSource dataSource() throws Exception {
		System.out.println("DataSourceJNDI");
		return (DataSource) jndiObjectFactoryBean().getObject();

	}
}
