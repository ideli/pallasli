package com.shineyue.htmldesign.mybatis;

//@Configuration
//@EnableTransactionManagement
//@MapperScan("com.*.*.mapper")
public class DataBaseConfig {

	// private final Logger log = LoggerFactory.getLogger(DataBaseConfig.class);
	//
	// @Bean
	// @Primary
	// @ConfigurationProperties(prefix = "datasource.primary")
	// public DataSource dataSource() {
	// log.debug("Configuring Datasource");
	// return new DruidDataSource();
	// }
	//
	// @Bean
	// public PlatformTransactionManager txManager() {
	// return new DataSourceTransactionManager(dataSource());
	// }
	//
	// @Bean
	// public SqlSessionFactory sqlSessionFactoryBean() throws Exception {
	//
	// SqlSessionFactoryBean sqlSessionFactoryBean = new
	// SqlSessionFactoryBean();
	// sqlSessionFactoryBean.setDataSource(dataSource());
	//
	// PathMatchingResourcePatternResolver resolver = new
	// PathMatchingResourcePatternResolver();
	//
	// sqlSessionFactoryBean.setMapperLocations(resolver
	// .getResources("classpath:/mapper/*.xml"));
	// return sqlSessionFactoryBean.getObject();
	// }

}
