package com.shineyue.htmldesign.service.impl;

import com.shineyue.htmldesign.mapper.UserMapper;
import com.shineyue.htmldesign.model.User;
import com.shineyue.htmldesign.service.UserService;

public class UserServiceImpl implements UserService {
	private UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public User getUserById(User user) {
		return userMapper.getUserById(user.getId());
	}

	public void create(String sql) {
		userMapper.create(sql);

	}
}
