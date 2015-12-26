package com.pallas.db.analysis.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDao {
	private final String DRIVER = "org.h2.Driver";
	// private final String URL = "jdbc:h2:~/test";
	// private final String URL = "jdbc:h2:mem:";
	private final String URL = "jdbc:h2:./h2db/pallasli";
	private final String USER = "sa";
	private final String PASS = "sa";
	protected Connection conn = null;// 数据库连接对象
	protected Statement stmt = null;
	protected PreparedStatement ps = null;// 数据库执行对象
	protected ResultSet rs = null;// 数据库临时结果集

	/**
	 * 获取数据库连接Connection对象
	 * 
	 * @return
	 */
	// private Server server;

	protected Connection getConn() {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASS);
			if (conn != null)
				return conn;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭动态连接
	 * 
	 * @param conn
	 * @param ps
	 */
	protected void closeLink(Connection conn, PreparedStatement ps) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	/**
	 * 关闭动态连接(重载方法)
	 * 
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	protected void closeLink(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	protected boolean insertDelete(String sql) {
		try {
			this.getConn();
			stmt = conn.createStatement();
			// ps =this.getConn().prepareStatement(sql);
			System.out.println(stmt);
			if (stmt != null)
				return stmt.execute(sql);
			// if (ps != null)
			// return ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeLink(conn, ps);
		}
		return false;
	}

	public static void main(String[] args) throws SQLException,
			ClassNotFoundException {

		// ApplicationContext context = new ClassPathXmlApplicationContext(
		// "spring-context.xml");
		//
		// Server h2Server = context.getBean(Server.class);
		// System.out.println(h2Server.getStatus());

		// H2ServerTest test = new H2ServerTest();
		// test.crudTest();

		// ((AbstractApplicationContext) context).close();

		BaseDao b = new BaseDao();
		System.out.println(b.insertDelete("drop table if exists AAA"));

		System.out
				.println(b
						.insertDelete("create table AAA(Id int primary key,name varchar,sex varchar ,age int)"));

		System.out
				.println(b
						.insertDelete("insert into AAA(Id,name,sex ,age) values (1,'2','man',30)"));
		System.out.println(b.insertDelete("select * from AAA"));
	}
}
