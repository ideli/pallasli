package com.pallasli.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pallasli.bean.TestBean;
import com.pallasli.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	@Autowired(required = false)
	private TestBean testBean;

	@Override
	public void save() {
		System.out.println("UserRepository Save...");
		System.out.println(testBean);
	}

	@Override
	public List<Map<String, Object>> findALL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TestBean> findALLDepts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int bid) {
		// TODO Auto-generated method stub
		return 0;
	}

}