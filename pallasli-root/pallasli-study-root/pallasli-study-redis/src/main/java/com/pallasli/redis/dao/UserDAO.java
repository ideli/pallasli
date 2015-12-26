package com.pallasli.redis.dao;

import com.pallasli.redis.bean.User;

public interface UserDAO {
	public void saveUser(final User user);

	public User getUser(final long id);
}
