package com.pallasli.sql.mybatis;

import java.io.InputStream;
import java.net.URL;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	public static SqlSession session;

	private static SqlSessionFactory sessionFactory;

	public static SqlSession getSession() {
		String resource = "";
		URL url = MyBatisUtil.class.getResource("/");
		if (url != null)
			resource = url.getPath();
		if (resource != null)
			resource += "org/mybatis/jdbc/config.xml";
		if (resource.startsWith("/")) {
			resource = resource.substring(1);
		}
		resource = "config.xml";
		return getSession(resource);
	}

	public static SqlSession getSession(String resource) {
		if (session == null) {
			// mybatis的配置文件
			// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
			InputStream is = MyBatisUtil.class.getClassLoader()
					.getResourceAsStream(resource);
			// 构建sqlSession的工厂
			sessionFactory = new SqlSessionFactoryBuilder().build(is);
			// 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
			// Reader reader = Resources.getResourceAsReader(resource);
			// 构建sqlSession的工厂
			// SqlSessionFactory sessionFactory = new
			// SqlSessionFactoryBuilder().build(reader);
			// 创建能执行映射文件中sql的sqlSession
			session = sessionFactory.openSession();
		} else {
			session = sessionFactory.openSession();
		}
		return session;

	}

	public static SqlSessionFactory getSqlSessionFactory() {

		getSession();
		return sessionFactory;
	}

}
