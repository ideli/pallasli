package com.pallasli.website.cache.redis.app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * RedisApp之spring的测试类
 *
 * @author yangzhilong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisApp2Test {
	private JedisPool pool;
	private ApplicationContext app;

	@Before
	public void before() {
		app = new ClassPathXmlApplicationContext("redisapp2-spring-config.xml");
		pool = app.getBean(JedisPool.class);
	}

	@Test
	public void test() {
		Jedis jedis = pool.getResource();
		jedis.set("name", "hello");

		String value = jedis.get("name");
		System.out.println("get value :" + value);

		pool.close();
	}

	@After
	public void after() {
		System.out.println("end~~~");
	}
}