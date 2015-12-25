package com.pallasli.test;

import org.apache.ibatis.session.SqlSessionFactory;

import com.pallasli.sql.mybatis.MyBatisUtil;

public class TestDemoService {

	static SqlSessionFactory sqlSessionFactory = null;
	static {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	}

	// // @Test
	// public void testAdd() {
	// SqlSession sqlSession = sqlSessionFactory.openSession();
	// try {
	// GenerateMapper userMapper = sqlSession
	// .getMapper(GenerateMapper.class);
	// Generate user = new Generate();
	// userMapper.insert(user);
	// sqlSession.commit();
	// } finally {
	// sqlSession.close();
	// }
	// }
	//
	// // @Test
	// public void getUser() {
	// SqlSession sqlSession = sqlSessionFactory.openSession();
	// try {
	// GenerateMapper userMapper = sqlSession
	// .getMapper(GenerateMapper.class);
	// GenerateExample example = new GenerateExample();
	// List<Generate> userlist = userMapper.selectByExample(example);
	// System.out.println("id: " + userlist.size());
	// } finally {
	// sqlSession.close();
	// }
	// }

}
