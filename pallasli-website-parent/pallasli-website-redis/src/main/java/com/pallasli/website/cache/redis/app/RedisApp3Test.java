package com.pallasli.website.cache.redis.app;

import java.io.Serializable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * RedisApp之spring redisTemplate的测试类
 *
 * @author yangzhilong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisApp3Test {
	private ApplicationContext app;
	private RedisTemplate<Serializable, Serializable> redisTemplate;
	private StringRedisTemplate stringRedisTemplate;

	@SuppressWarnings("unchecked")
	@Before
	public void before() {
		app = new ClassPathXmlApplicationContext("spring-config.xml");
		redisTemplate = (RedisTemplate<Serializable, Serializable>) app.getBean("redisTemplate");
		stringRedisTemplate = app.getBean(StringRedisTemplate.class);
	}

	@Test
	public void test() {
		System.out.println("use StringRedisTemplate save value");
		// 保存
		stringRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.set(stringRedisTemplate.getStringSerializer().serialize("name"),
						stringRedisTemplate.getStringSerializer().serialize("hello"));
				return null;
			}
		});

		// 取值
		String value = stringRedisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return stringRedisTemplate.getStringSerializer()
						.deserialize(connection.get(stringRedisTemplate.getStringSerializer().serialize("name")));
			}

		});

		System.out.println("use StringRedisTemplate get value :" + value);

		System.out.println("use RedisTemplate append value");
		// 追加
		redisTemplate.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				connection.append(redisTemplate.getStringSerializer().serialize("name"),
						redisTemplate.getStringSerializer().serialize(" redis"));
				return null;
			}
		});

		// 取值
		value = redisTemplate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				return redisTemplate.getStringSerializer()
						.deserialize(connection.get(redisTemplate.getStringSerializer().serialize("name")));
			}
		});
		System.out.println("use RedisTemplate get value :" + value);
	}

	@After
	public void after() {
		System.out.println("end~~~");
	}
}