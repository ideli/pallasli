package com.pallasli.study.apachecommons.dbutils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * DbUtils ：提供如关闭连接、装载JDBC驱动程序等常规工作的工具类，里面的所有方法都是静态的。主要方法如下： public static void
 * close(…) throws java.sql.SQLException：
 * DbUtils类提供了三个重载的关闭方法。这些方法检查所提供的参数是不是NULL，如果不是的话，它们就关闭Connection、
 * Statement和ResultSet。 public static void closeQuietly(…):
 * 这一类方法不仅能在Connection、Statement和ResultSet为NULL情况下避免关闭，
 * 还能隐藏一些在程序中抛出的SQLEeception。 public static void
 * commitAndCloseQuietly(Connection conn)： 用来提交连接，然后关闭连接，并且在关闭连接时不抛出SQL异常。
 * public static boolean loadDriver(java.lang.String
 * driverClassName)：这一方装载并注册JDBC驱动程序，如果成功就返回true。使用该方法，
 * 你不需要捕捉这个异常ClassNotFoundException。
 * 
 * 
 * @author lyt1987
 *
 */
public class JdbcUtils {

	private static DataSource ds = null;// 在静态代码块中创建数据库连接池

	static {
		try {

			// 通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
			ds = new ComboPooledDataSource("MySQL");// 使用C3P0的命名配置来创建数据源

		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		// 从数据源中获取数据库连接
		return ds.getConnection();
	}

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

	public static DataSource getDataSource() {
		// TODO Auto-generated method stub
		return ds;
	}
}