package com.pallas.activiti.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.pallas.mybatis.factory.MyBatisUtil;
import com.pallas.service.DemoService;
import com.pallas.service.pojo.SysUser;

public class TestDemoService {

	static SqlSessionFactory sqlSessionFactory = null;
	static {
		sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
	}

	// @Test
	public void testAdd() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			DemoService userMapper = sqlSession.getMapper(DemoService.class);
			SysUser user = new SysUser("tom", new Integer(5));
			userMapper.insertUser(user);
			sqlSession.commit();
		} finally {
			sqlSession.close();
		}
	}

	// @Test
	public void getUser() {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			DemoService userMapper = sqlSession.getMapper(DemoService.class);
			SysUser user = userMapper.getUser(4l);
			System.out.println("id: " + user.getId());
		} finally {
			sqlSession.close();
		}
	}

}
