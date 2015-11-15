package com.pallasli.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.pallasli.designer.sys.SqlPropUtils;

public class DbOper {
	static Connection conn = null;

	public static void open() {

		Properties p = SqlPropUtils.getProperties("database.properties");
		String url = p.getProperty("jdbc.url");
		String user = p.getProperty("jdbc.username");
		String pwd = p.getProperty("jdbc.password");
		String driver = p.getProperty("jdbc.driver");
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// //h2自带的连接池
		// JdbcConnectionPool cp =
		// JdbcConnectionPool.create(url,username,password);
		// //通过连接池获取连接
		// Connection conn = cp.getConnection();
	}

	public static void close() {
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws Exception {
		open();
		close();
	}

	public static Connection getConn() {
		if (conn == null) {
			open();
		}
		return conn;
	}
}
