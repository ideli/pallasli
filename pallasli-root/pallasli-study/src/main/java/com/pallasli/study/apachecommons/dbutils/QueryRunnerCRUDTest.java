package com.pallasli.study.apachecommons.dbutils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

/**
 * 
 * 
 * 一、commons-dbutils简介
 * 
 * commons-dbutils 是 Apache 组织提供的一个开源
 * JDBC工具类库，它是对JDBC的简单封装，学习成本极低，并且使用dbutils能极大简化jdbc编码的工作量，同时也不会影响程序的性能。
 * 因此dbutils成为很多不喜欢hibernate的公司的首选。
 * 
 * commons-dbutilsAPI介绍：
 * 
 * org.apache.commons.dbutils.QueryRunner
 * org.apache.commons.dbutils.ResultSetHandler 工具类
 * 
 * org.apache.commons.dbutils.DbUtils 二、QueryRunner类使用讲解
 * 
 * 该类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。
 * QueryRunner类提供了两个构造方法：
 * 
 * 默认的构造方法 需要一个 javax.sql.DataSource 来作参数的构造方法。 2.1、QueryRunner类的主要方法
 * 
 * public Object query(Connection conn, String sql, Object[] params,
 * ResultSetHandler rsh) throws
 * SQLException：执行一个查询操作，在这个查询中，对象数组中的每个元素值被用来作为查询语句的置换参数。该方法会自行处理
 * PreparedStatement 和 ResultSet 的创建和关闭。 public Object query(String sql,
 * Object[] params, ResultSetHandler rsh) throws SQLException:
 * 几乎与第一种方法一样；唯一的不同在于它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource)
 * 或使用的setDataSource 方法中重新获得 Connection。 public Object query(Connection conn,
 * String sql, ResultSetHandler rsh) throws SQLException : 执行一个不需要置换参数的查询操作。
 * public int update(Connection conn, String sql, Object[] params) throws
 * SQLException:用来执行一个更新（插入、更新或删除）操作。 public int update(Connection conn, String
 * sql) throws SQLException：用来执行一个不需要置换参数的更新操作。
 * 
 * 
 * 
 * @ClassName: DBUtilsCRUDTest
 * @Description:使用dbutils框架的QueryRunner类完成CRUD,以及批处理
 * @author: 孤傲苍狼
 * @date: 2014-10-5 下午4:56:44
 *
 */
public class QueryRunnerCRUDTest {

	/*
	 * 测试表 create table users( id int primary key auto_increment, name
	 * varchar(40), password varchar(40), email varchar(60), birthday date );
	 */

	@Test
	public void add() throws SQLException {
		// 将数据源传递给QueryRunner，QueryRunner内部通过数据源获取数据库连接
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
		Object params[] = { "孤傲苍狼", "123", "gacl@sina.com", new Date() };
		// Object params[] = {"白虎神皇","123", "gacl@sina.com", "1988-05-07"};
		qr.update(sql, params);
	}

	@Test
	public void delete() throws SQLException {

		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "delete from users where id=?";
		qr.update(sql, 1);

	}

	@Test
	public void update() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "update users set name=? where id=?";
		Object params[] = { "ddd", 5 };
		qr.update(sql, params);
	}

	@Test
	public void find() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users where id=?";
		Object params[] = { 2 };
		User user = (User) qr.query(sql, params, new BeanHandler(User.class));
		System.out.println(user.getBirthday());
	}

	@Test
	public void getAll() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "select * from users";
		List list = (List) qr.query(sql, new BeanListHandler(User.class));
		System.out.println(list.size());
	}

	/**
	 * @Method: testBatch
	 * @Description:批处理
	 * @Anthor:孤傲苍狼
	 *
	 * @throws SQLException
	 */
	@Test
	public void testBatch() throws SQLException {
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into users(name,password,email,birthday) values(?,?,?,?)";
		Object params[][] = new Object[10][];
		for (int i = 0; i < 10; i++) {
			params[i] = new Object[] { "aa" + i, "123", "aa@sina.com", new Date() };
		}
		qr.batch(sql, params);
	}

	// 用dbutils完成大数据（不建议用）
	/***************************************************************************
	 * create table testclob ( id int primary key auto_increment, resume text );
	 **************************************************************************/
	@Test
	public void testclob() throws SQLException, IOException {
		QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
		String sql = "insert into testclob(resume) values(?)"; // clob
		// 这种方式获取的路径，其中的空格会被使用“%20”代替
		String path = QueryRunnerCRUDTest.class.getClassLoader().getResource("data.txt").getPath();
		// 将“%20”替换回空格
		path = path.replaceAll("%20", " ");
		FileReader in = new FileReader(path);
		char[] buffer = new char[(int) new File(path).length()];
		in.read(buffer);
		SerialClob clob = new SerialClob(buffer);
		Object params[] = { clob };
		runner.update(sql, params);
	}
}