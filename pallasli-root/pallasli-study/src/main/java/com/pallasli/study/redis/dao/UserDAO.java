package com.pallasli.study.redis.dao;

import com.pallasli.study.redis.bean.User;

public interface UserDAO {
	public void saveUser(final User user);

	public User getUser(final long id);
}
