package com.pallasli.study.apachecommons.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 
 * 
 * 三、开源数据库连接池
 * 
 * 现在很多WEB服务器(Weblogic, WebSphere,
 * Tomcat)都提供了DataSoruce的实现，即连接池的实现。通常我们把DataSource的实现，按其英文含义称之为数据源，
 * 数据源中都包含了数据库连接池的实现。 也有一些开源组织提供了数据源的独立实现：
 * 
 * DBCP 数据库连接池 C3P0 数据库连接池 在使用了数据库连接池之后，在项目的实际开发中就不需要编写连接数据库的代码了，直接从数据源获得数据库的连接。
 * 
 * 3.1、DBCP数据源
 * 
 * DBCP 是 Apache 软件基金组织下的开源连接池实现，要使用DBCP数据源，需要应用程序应在系统中增加如下两个 jar 文件：
 * 
 * Commons-dbcp.jar：连接池的实现 Commons-pool.jar：连接池实现的依赖库 Tomcat
 * 的连接池正是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用程序独立使用。
 * 
 * 3.2、在应用程序中加入dbcp连接池
 * 
 * 1.导入相关jar包 commons-dbcp-1.2.2.jar、commons-pool.jar
 * 2、在类目录下加入dbcp的配置文件：dbcpconfig.properties
 * 
 * 3.3、C3P0数据源
 * 
 * C3P0是一个开源的JDBC连接池，它实现了数据源和JNDI绑定，支持JDBC3规范和JDBC2的标准扩展。目前使用它的开源项目有Hibernate，
 * Spring等。C3P0数据源在项目开发中使用得比较多。
 * 
 * c3p0与dbcp区别 dbcp没有自动回收空闲连接的功能 c3p0有自动回收空闲连接功能 3.4、在应用程序中加入C3P0连接池
 * 
 * 1.导入相关jar包
 * c3p0-0.9.2-pre1.jar、mchange-commons-0.2.jar，如果操作的是Oracle数据库，那么还需要导入c3p0-
 * oracle-thin-extras-0.9.2-pre1.jar 2、在类目录下加入C3P0的配置文件：c3p0-config.xml
 * 
 * 
 * 
 * @ClassName: JdbcUtils_C3P0
 * @Description: 数据库连接工具类
 * @author: 孤傲苍狼
 * @date: 2014-10-4 下午6:04:36
 *
 */
public class JdbcUtils_C3P0 {

	private static ComboPooledDataSource ds = null;

	// 在静态代码块中创建数据库连接池
	static {
		try {
			// 通过代码创建C3P0数据库连接池
			/*
			 * ds = new ComboPooledDataSource();
			 * ds.setDriverClass("com.mysql.jdbc.Driver");
			 * ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcstudy");
			 * ds.setUser("root"); ds.setPassword("XDP");
			 * ds.setInitialPoolSize(10); ds.setMinPoolSize(5);
			 * ds.setMaxPoolSize(20);
			 */

			// 通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
			// ds = new ComboPooledDataSource();//使用C3P0的默认配置来创建数据源
			ds = new ComboPooledDataSource("MySQL");// 使用C3P0的命名配置来创建数据源

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * @Method: getConnection
	 * @Description: 从数据源中获取数据库连接
	 * @Anthor:孤傲苍狼
	 * @return Connection
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// 从数据源中获取数据库连接
		return ds.getConnection();
	}

	/**
	 * @Method: release
	 * @Description: 释放资源， 释放的资源包括Connection数据库连接对象，负责执行SQL命令的Statement对象，
	 *               存储查询结果的ResultSet对象
	 * @Anthor:孤傲苍狼
	 *
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void release(Connection conn, Statement st, ResultSet rs) {
		if (rs != null) {
			try {
				// 关闭存储查询结果的ResultSet对象
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		if (st != null) {
			try {
				// 关闭负责执行SQL命令的Statement对象
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				// 将Connection连接对象还给数据库连接池
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}