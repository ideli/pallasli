package com.pallasli.study.cache.redis.spring;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

public class UserDAOImpl implements UserDAO {

	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
		return redisTemplate;
	}

	public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@Override
	public void saveUser(final User user) {
		redisTemplate.execute(new RedisCallback<Object>() {

			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(redisTemplate.getStringSerializer().serialize("user.uid." + user.getId()),
						redisTemplate.getStringSerializer().serialize(user.getName()));
				return null;
			}
		});
	}

	@Override
	public User getUser(final long id) {
		return redisTemplate.execute(new RedisCallback<User>() {
			@Override
			public User doInRedis(RedisConnection connection) throws DataAccessException {
				byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
				if (connection.exists(key)) {
					byte[] value = connection.get(key);
					String name = redisTemplate.getStringSerializer().deserialize(value);
					User user = new User();
					user.setName(name);
					user.setId(id);
					return user;
				}
				return null;
			}
		});
	}

}
