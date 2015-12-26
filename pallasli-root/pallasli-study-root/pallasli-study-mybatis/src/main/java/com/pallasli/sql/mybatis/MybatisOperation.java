package com.pallasli.sql.mybatis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class MybatisOperation {
	private static MybatisOperation singlon = null;
	static SqlSessionFactory sqlSessionFactory = null;

	public static MybatisOperation instance() {
		if (singlon == null) {
			sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
			singlon = new MybatisOperation();
		}
		return singlon;
	}

	public ResultSet execQuery(String sql) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		ResultSet ret = null;
		try {
			Connection conn = sqlSession.getConnection();
			Statement stmt = conn.createStatement();
			ret = stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return ret;
	}

	public boolean execSql(String sql, Object obj) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		boolean ret = false;
		int i = sqlSession.update(sql, obj);
		if (i > 0) {
			ret = true;
		}
		sqlSession.commit();

		return ret;
	}

	public boolean createTable() {

		try {
			SqlSession sqlSession = sqlSessionFactory.openSession();
			Connection conn = sqlSession.getConnection();
			Statement stat = conn.createStatement();

			// create table
			stat.execute("create table Generate (ID VARCHAR(20), NAME longtext, MONEY DOUBLE, REVISION int)");

			stat.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//
		//
		// String sql =
		// "create table Generate (ID VARCHAR(20), NAME longtext, MONEY DOUBLE, REVISION int)";
		// sql = "update generate set dd=1";
		// SqlSession sqlSession = sqlSessionFactory.openSession();
		// sqlSession.getConnection();
		// boolean ret = false;
		// int i = sqlSession.getMapper("").update(sql, null);
		// if (i > 0) {
		// ret = true;
		// }
		// sqlSession.commit();
		return true;
	}
}
