package com.pallasli.study.spring.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.pallasli.study.spring.config.DataSourceConfig;

@Configuration
// 启用注解事务管理，使用CGLib代理
@EnableTransactionManagement(proxyTargetClass = true)
@Import({ DataSourceConfig.class })
public class DaoConfig {

	// @Value("${hibernate.dialect}")
	// String hibernate_dialect;
	// @Value("${hibernate.show_sql}")
	// String hibernate_show_sql;

	/**
	 * 描述 : <负责解析资源文件>. <br>
	 * <p>
	 * <这个类必须有，而且必须声明为static，否则不能正常解析>
	 * </p>
	 * 
	 * @return
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placehodlerConfigurer() {
		System.out.println("PropertySourcesPlaceholderConfigurer");
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Resource(name = "dataSource")
	public DataSource dataSource;

	// @Bean(name = "sessionFactory")
	// public LocalSessionFactoryBean localSessionFactoryBean() {
	// System.out.println("sessionFactory");
	// LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	// sessionFactory.setDataSource(dataSource);
	// String[] packagesToScan = new String[] { "web.function.**.model.oracle"
	// };
	// sessionFactory.setPackagesToScan(packagesToScan);
	//
	// Properties hibernateProperties = new Properties();
	// hibernateProperties.setProperty("hibernate.dialect", hibernate_dialect);
	// hibernateProperties.setProperty("hibernate.show_sql",
	// hibernate_show_sql);
	// hibernateProperties.setProperty(
	// "hibernate.current_session_context_class",
	// "org.springframework.orm.hibernate4.SpringSessionContext");
	// sessionFactory.setHibernateProperties(hibernateProperties);
	//
	// return sessionFactory;
	//
	// }
	//
	// @Bean(name = "hibernateDAO")
	// public CP_Hibernate4DAOImpl hibernate4Dao() {
	// System.out.println("hibernateDAO");
	// CP_Hibernate4DAOImpl dao = new CP_Hibernate4DAOImpl();
	// dao.setSessionFactory(localSessionFactoryBean().getObject());
	// return dao;
	// }
	//
	// @Bean(name = "transactionManager")
	// public HibernateTransactionManager hibernateTransactionManager() {
	// System.out.println("transactionManager");
	// HibernateTransactionManager hibernateTransactionManager = new
	// HibernateTransactionManager();
	// hibernateTransactionManager.setSessionFactory(localSessionFactoryBean()
	// .getObject());
	// return hibernateTransactionManager;
	// }
}
