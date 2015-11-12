package com.pallasli.jdbc;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GjjmxDataSource implements DataSource {
	private static final Log log = LogFactory.getLog(GjjmxDataSource.class);
	private final int DBType = 4;
	private String driverClassName, dbURL;
	private final String user, password;
	private String JNDI_Name;
	private final String Sys = "gjj";
	private String conntype;
	private final Properties dbProps = new Properties();
	private DataSource ds = null;

	public GjjmxDataSource(String driverClassName, String dbURL, String user,
			String password) throws ClassNotFoundException,
			InstantiationException, SQLException, IllegalAccessException {
		this.driverClassName = driverClassName;
		this.dbURL = dbURL;
		this.user = user;
		this.password = password;
		this.conntype = "0";

		dbProps.setProperty("user", user);
		dbProps.setProperty("password", password);

		if (driverClassName.startsWith("com.sybase.jdbc2")) {
			dbProps.setProperty("charset", "cp936");
		} else if (driverClassName
				.equals("com.ibm.as400.access.AS400JDBCDriver")) {
			dbProps.setProperty("date format", "iso");
		}

		else {
			log.debug("ע����ݿ���" + this.driverClassName);
		}
		DriverManager.registerDriver((Driver) Class.forName(
				this.driverClassName).newInstance());
	}

	public GjjmxDataSource(String driverClassName, String dbURL, String user,
			String password, String initialSize, String maxActive,
			String maxIdle, String minIdle, String maxWait,
			String removeAbandoned, String removeAbandonedTimeout,
			String logAbandoned, String testOnBorrow)
			throws ClassNotFoundException, InstantiationException,
			SQLException, IllegalAccessException {
		this(driverClassName, dbURL, user, password);
		this.conntype = "1";

		dbProps.setProperty("driverClassName", driverClassName);
		dbProps.setProperty("url", dbURL);

		dbProps.setProperty("username", user);
		dbProps.setProperty("initialSize", initialSize);
		dbProps.setProperty("maxActive", maxActive);
		dbProps.setProperty("maxIdle", maxIdle);
		dbProps.setProperty("minIdle", minIdle);
		dbProps.setProperty("maxWait", maxWait);
		dbProps.setProperty("removeAbandoned", removeAbandoned);
		dbProps.setProperty("removeAbandonedTimeout", removeAbandonedTimeout);
		dbProps.setProperty("logAbandoned", logAbandoned);
		dbProps.setProperty("testOnBorrow", testOnBorrow);
		log.debug("dbProps=" + dbProps);
		try {
			ds = BasicDataSourceFactory.createDataSource(dbProps);
		} catch (Exception e) {
			throw new SQLException("��ʼ�����ӳس���:" + e.getMessage());
		}
	}

	public GjjmxDataSource(String factory, String url, String user,
			String password, String JNDI_Name) throws ClassNotFoundException,
			InstantiationException, SQLException, IllegalAccessException {
		this.user = user;
		this.password = password;
		this.JNDI_Name = JNDI_Name;
		this.conntype = "2";

		try {
			Context ctx = new InitialContext();
			ds = (javax.sql.DataSource) ctx.lookup(this.JNDI_Name);
			/*******************
			 * tomcat ********************* Context envCtx = (Context)
			 * ctx.getNameParser("java:comp/env"); ds = (DataSource)
			 * envCtx.lookup(this.JNDI_Name);
			 ***********************************************/
		} catch (Exception e) {
			throw new SQLException("��ʼ����������:" + e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		if (this.conntype.equals("0")) {
			return DriverManager.getConnection(dbURL, dbProps);
		} else if (conntype.equals("1")) {
			return ds.getConnection();
		} else if (conntype.equals("2")) {
			return ds.getConnection();
		} else {
			throw new SQLException("δ������������ͣ�");
		}
	}

	public void coloseConnection(Connection conn) throws SQLException {
		if (conn != null && !conn.isClosed()) {
			conn.close();
		}
	}

	public String getDriverClassName() throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			this.driverClassName = conn.getMetaData().getDriverName();
		} finally {
			coloseConnection(conn);
		}
		return driverClassName;
	}

	public String getSys() {
		return this.Sys;
	}

	public int getDBType() {
		return this.DBType;
	}

	public String getDbURL() throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			this.dbURL = conn.getMetaData().getURL();
			// this.driverClassName = conn.getMetaData().getDriverName();
		} finally {
			coloseConnection(conn);
		}
		return this.dbURL;
	}

	public String getUser() {
		return this.user;
	}

	public String getPassword() {
		return this.password;
	}

	public String getDataBaseName() throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			this.dbURL = conn.getMetaData().getURL();
			if ((dbURL.indexOf("/") != -1 && this.DBType == 4)
					|| (dbURL.indexOf("as400") != -1)) {
				return dbURL.substring(dbURL.lastIndexOf("/") + 1);
			} else {
				return conn.getMetaData().getUserName();
			}
		} finally {
			coloseConnection(conn);
		}

	}

	public String getDataBaseURL() throws SQLException {
		Connection conn = null;
		try {
			conn = getConnection();
			this.dbURL = conn.getMetaData().getURL();
			// this.driverClassName = conn.getMetaData().getDriverName();
		} finally {
			coloseConnection(conn);
		}

		if (dbURL.indexOf("/") != -1) {
			return dbURL.substring(0, dbURL.lastIndexOf("/"));
		} else {
			return dbURL.substring(0, dbURL.lastIndexOf(":"));
		}
	}

	public Connection getConnection(String username, String password)
			throws SQLException {
		throw new SQLException("Not supported");
	}

	public int getLoginTimeout() throws SQLException {
		throw new SQLException("Not supported");
	}

	public PrintWriter getLogWriter() throws SQLException {
		throw new SQLException("Not supported");
	}

	public void setLoginTimeout(int seconds) throws SQLException {
		throw new SQLException("Not supported");
	}

	public synchronized void setLogWriter(PrintWriter out) throws SQLException {
		throw new SQLException("Not supported");
	}

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

}
