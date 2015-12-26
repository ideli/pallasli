package com.pallasli.springboot;

//import java.io.File;
//
//import javax.servlet.Filter;
//import javax.sql.DataSource;
//
//import net.sf.log4jdbc.Log4jdbcProxyDataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.web.filter.CharacterEncodingFilter;

//@Configuration
public class ApplicationConfig {

	// @Autowired
	// DataSourceProperties dataSourceProperties;
	// DataSource dataSource;
	//
	// @Bean
	// DataSource realDataSource() {
	// DataSourceBuilder factory = DataSourceBuilder
	// .create(this.dataSourceProperties.getClassLoader())
	// .url(this.dataSourceProperties.getUrl())
	// .username(this.dataSourceProperties.getUsername())
	// .password(this.dataSourceProperties.getPassword());
	// this.dataSource = factory.build();
	// return new Log4jdbcProxyDataSource(this.dataSource);
	// }
	//
	// @Bean
	// public SqlSessionFactory getSqlSessionFactory() {
	// Resource[] resources = new Resource[] {
	// getMapperXMLPathResource(UserMapper.class)
	//
	// };
	//
	// SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
	// factoryBean.setDataSource(realDataSource());
	// factoryBean.setMapperLocations(resources);
	//
	// SqlSessionFactory sqlSessionFactory = null;
	// try {
	// sqlSessionFactory = factoryBean.getObject();
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.exit(0);
	// }
	// org.apache.ibatis.session.Configuration configuration = sqlSessionFactory
	// .getConfiguration();
	// configuration.setMapUnderscoreToCamelCase(true);
	//
	// return sqlSessionFactory;
	// }
	//
	// public static Resource getMapperXMLPathResource(Class<?> clazz) {
	// return new ClassPathResource(clazz.getName()
	// .replace(".", File.separator).concat(".xml"));
	// }
	//
	// @Bean
	// public UserMapper getUserMapper() {
	// SqlSessionTemplate sessionTemplate = new SqlSessionTemplate(
	// getSqlSessionFactory());
	// return sessionTemplate.getMapper(UserMapper.class);
	// }
	//
	// @Order(Ordered.HIGHEST_PRECEDENCE)
	// @Bean
	// Filter characterEncodingFilter() {
	// CharacterEncodingFilter filter = new CharacterEncodingFilter();
	// filter.setEncoding("UTF-8");
	// filter.setForceEncoding(true);
	// return filter;
	// }
}
