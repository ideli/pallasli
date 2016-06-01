package com.pallasli.study.cache.redis.spring;

public interface UserDAO {
	public void saveUser(final User user);

	public User getUser(final long id);
}
