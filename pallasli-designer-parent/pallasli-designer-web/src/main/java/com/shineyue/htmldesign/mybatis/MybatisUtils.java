package com.shineyue.htmldesign.mybatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.shineyue.htmldesign.contoller.SimpleController;

public class MybatisUtils {
	public static SqlSession session;
	public static SqlSessionFactory sessionFactory;

	public static SqlSession getSession() {
		if (session == null) {
			// mybatis的配置文件
			String resource = "configuration.xml";
			// 使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
			InputStream is = SimpleController.class.getClassLoader()
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
			// session = sessionFactory.openSession();
		}
		return session;

	}
}
