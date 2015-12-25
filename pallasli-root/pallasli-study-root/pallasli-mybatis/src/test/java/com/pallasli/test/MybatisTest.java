package com.pallasli.test;



//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import demo.Account;
//import demo.AccountDAO;
//import demo.AppContextCore;
//import demo.BaseDaoSupport;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:demo/atwasoft.framework.core.xml")
public class MybatisTest {
	// @Autowired
	// private AccountDAO accountDAO;
	//
	// // @Autowired
	// private BaseDaoSupport baseDaoSupport;
	//
	// Log log = LogFactory.getLog(getClass());
	//
	// @Test
	// public void saveAccount() {
	//
	// log.info("系统初始化应用环境");
	//
	// ApplicationContext context = AppContextCore.getContext();
	//
	// if (log.isInfoEnabled()) {
	//
	// String[] beanNames = context.getBeanDefinitionNames();
	//
	// for (String beanName : beanNames) {
	// log.info("load bean name:{}" + beanName);
	// }
	// }
	//
	// Map<String, Object> beansWithAnno = context
	// .getBeansWithAnnotation(WebService.class);
	//
	// log.info("共加载WEB服务组件数：{}" + beansWithAnno == null
	// || beansWithAnno.isEmpty() ? 0 : beansWithAnno.keySet().size());
	//
	// if (log.isInfoEnabled()) {
	//
	// for (String key : beansWithAnno.keySet()) {
	// log.info("load webservice:{},{}" + key + beansWithAnno.get(key));
	// }
	//
	// }
	//
	// String[] repoBeans = context
	// .getBeanNamesForAnnotation(Repository.class);
	// log.info("共加载数据库访问组件数：{}" + repoBeans == null ? 0 : repoBeans.length);
	//
	// if (log.isInfoEnabled()) {
	//
	// for (String repo : repoBeans) {
	// log.info("load repostiory:{}" + repo);
	// }
	//
	// }
	//
	// String[] serviceBeans = context
	// .getBeanNamesForAnnotation(Service.class);
	//
	// log.info("共加载业务逻辑组件数：{}" + serviceBeans == null ? 0
	// : serviceBeans.length);
	//
	// if (log.isInfoEnabled()) {
	//
	// for (String service : serviceBeans) {
	// log.info("load buz service:{}" + service);
	// }
	//
	// }
	//
	// String businessKey = "d", user = "d";
	// Account account = new Account();
	// account.setMoney(1000.);
	// account.setName(user);
	// account.setId(businessKey);
	// account.setRevision(0);
	//
	// // Context.getCommandContext().getDbSqlSession().insert(account);
	//
	// /*
	// * JdbcTemplate tepl=new JdbcTemplate(dataSource);
	// *
	// * tepl.execute("insert into TEMP_BPM_TEST values('"+account.getId()+"','"
	// * +
	// * account.getName()+"',"+account.getMoney()+","+account.getRevision()+
	// * ")" );
	// */
	//
	// baseDaoSupport = (BaseDaoSupport) context.getBean("baseDaoSupport");
	// Map<String, String> map = new HashMap<String, String>();
	//
	// map.put("sql",
	// "create table TEMP_BPM_TEST (ID VARCHAR(20), NAME longtext, MONEY DOUBLE, REVISION int)");
	//
	// baseDaoSupport.getSqlSession().getMapper(AccountDAO.class)
	// .createTableSql(map);
	// baseDaoSupport.getSqlSession().getMapper(AccountDAO.class)
	// .insertAccount(account);
	// DataSource dataSource = (DataSource) context.getBean("dataSource");
	// JdbcTemplate tepl = new JdbcTemplate(dataSource);
	//
	// List<Map<String, Object>> ret = tepl
	// .queryForList("select id from TEMP_BPM_TEST");
	// for (Map<String, Object> map2 : ret) {
	// System.out.println(map2.get("id"));
	// System.out.println(map2.get("ID"));
	// }
	//
	// // Connection conn = baseDaoSupport.getSqlSession().getConnection();
	// // try {
	// //
	// // Statement stmt = conn.createStatement();
	// // stmt.execute("insert into TEMP_BPM_TEST(id) values('ddd')");
	// // stmt.execute("insert into TEMP_BPM_TEST(id) values('ddd')");
	// // conn.commit();
	// // ResultSet resultset = stmt
	// // .executeQuery("select id from TEMP_BPM_TEST");
	// // Array array = resultset.getArray(0);
	// // while (resultset.next()) {
	// // System.out.println(resultset.getString(0));
	// // }
	// //
	// // } catch (SQLException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	//
	// // accountDAO = (AccountDAO) context.getBean("accountDAO");
	// // accountDAO.insertAccount(account);
	//
	// }
}
