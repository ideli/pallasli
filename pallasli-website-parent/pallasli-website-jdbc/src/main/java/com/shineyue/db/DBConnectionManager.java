package com.shineyue.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DBConnectionManager {

	static private DBConnectionManager instance; // 唯一实例
	static private DataSource ds;

	private DBConnectionManager() {
		ds = setupDataSource();
	}

	private static DataSource setupDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		try {
			InputStream is = DBConnectionManager.class.getClassLoader()
					.getResourceAsStream("jdbc.properties");

			Properties dbProps = new Properties();
			dbProps.load(is);

			dataSource.setUrl(dbProps.getProperty("url"));
			dataSource.setUsername(dbProps.getProperty("username"));
			dataSource.setPassword(dbProps.getProperty("password"));
			dataSource.setDriverClassName(dbProps
					.getProperty("driverClassName"));

			dataSource.setInitialSize(new Integer(dbProps
					.getProperty("initialSize")));
			dataSource.setMaxActive(new Integer(dbProps
					.getProperty("maxActive")));
			dataSource.setMaxIdle(new Integer(dbProps.getProperty("maxIdle")));
			dataSource.setMinIdle(new Integer(dbProps.getProperty("minIdle")));
			dataSource.setMaxWait(new Long(dbProps.getProperty("maxWait")));

			dataSource.setRemoveAbandoned(true);
			dataSource.setRemoveAbandonedTimeout(60);

			dataSource.setTestOnReturn(false);
			dataSource.setTestOnBorrow(false);
			dataSource.setTimeBetweenEvictionRunsMillis(30000L);
			dataSource.setValidationQuery(dbProps
					.getProperty("select sysdate mc from dual"));
			dataSource.setTestWhileIdle(new Boolean(true));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataSource;
	}

	static synchronized public DBConnectionManager getInstance() {
		if (instance == null) {
			instance = new DBConnectionManager();
		}

		return instance;
	}

	public void freeConnection(Connection con) {
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		if (ds != null) {
			try {
				Connection conn = ds.getConnection();
				return conn;
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		DBConnectionManager db = DBConnectionManager.getInstance();
		db.getConnection();
		try {
			System.out.println("Creating connection.");
			conn = db.getConnection();
			System.out.println("Creating statement.");
			stmt = conn.createStatement();
			System.out.println("Executing statement.");
			String sql = "select * from dual";
			rset = stmt.executeQuery(sql);
			System.out.println("Results:");
			int numcols = rset.getMetaData().getColumnCount();
			while (rset.next()) {
				for (int i = 1; i <= numcols; i++) {
					System.out.print("\t" + rset.getString(i));
				}
				System.out.println("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		try {
			System.out.println("Creating connection.");
			conn = db.getConnection();
			System.out.println("Creating statement.");
			stmt = conn.createStatement();
			System.out.println("Executing statement.");
			String sql = "select * from dual";
			rset = stmt.executeQuery(sql);
			System.out.println("Results:");
			int numcols = rset.getMetaData().getColumnCount();
			while (rset.next()) {
				for (int i = 1; i <= numcols; i++) {
					System.out.print("\t" + rset.getString(i));
				}
				System.out.println("");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
			} catch (Exception e) {
			}
			try {
				stmt.close();
			} catch (Exception e) {
			}
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

}