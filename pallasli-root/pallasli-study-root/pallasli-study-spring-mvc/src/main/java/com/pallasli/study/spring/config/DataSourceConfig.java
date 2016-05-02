package com.pallasli.study.spring.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
// 加载资源文件
@PropertySource({ "classpath:/db.properties" })
public class DataSourceConfig {
	/*
	 * 绑定资源属性
	 */
	@Value("${jdbc.driver}")
	String driverClass;
	@Value("${jdbc.url}")
	String url;
	@Value("${jdbc.username}")
	String userName;
	@Value("${jdbc.password}")
	String passWord;

	@Bean(name = "dataSource")
	public DataSource dataSource() {

		System.out.println("DataSource");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClass);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(passWord);
		return dataSource;
	}

}
