package com.pallasli.sql.mybatis;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.pallasli.sql.Operation;

public class MybatisOperation extends Operation {
	static MybatisOperation singlon = null;
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
}
