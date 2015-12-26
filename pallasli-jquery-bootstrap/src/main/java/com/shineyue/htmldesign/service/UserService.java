package com.shineyue.htmldesign.service;

import com.shineyue.htmldesign.model.User;

public interface UserService {
	public User getUserById(User user);

	public void create(String sql);
}
