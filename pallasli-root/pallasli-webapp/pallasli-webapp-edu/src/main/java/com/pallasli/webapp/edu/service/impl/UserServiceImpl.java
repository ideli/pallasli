package com.pallasli.webapp.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallasli.webapp.edu.mapper.UserInfoMapper;
import com.pallasli.webapp.edu.model.UserInfoExample;
import com.pallasli.webapp.edu.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	@Override
	public String findUserNameByLoginName(String loginName) {
		UserInfoExample example = new UserInfoExample();
		example.createCriteria().andIdcardsEqualTo(loginName);
		example.or();
		example.createCriteria().andQqEqualTo(loginName);
		example.or();
		example.createCriteria().andMobileEqualTo(loginName);
		userInfoMapper.selectByExample(example);
		return null;
	}

}
