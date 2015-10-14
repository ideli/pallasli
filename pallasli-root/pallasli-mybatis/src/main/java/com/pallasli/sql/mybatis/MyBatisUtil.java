package com.pallasli.sql.mybatis;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	private static SqlSessionFactory sqlSessionFactory;

	public static void init() {
		String resource = MyBatisUtil.class.getResource("/").getPath()
				+ "org/mybatis/jdbc/config.xml";
		if (resource.startsWith("/")) {
			resource = resource.substring(1);
		}
		resource = "org/mybatis/jdbc/config.xml";
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader(resource);
		} catch (IOException e) {
			System.out.println(e.getMessage());

		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {

		init();
		return sqlSessionFactory;
	}

}
