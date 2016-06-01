package com.pallasli.study.redis.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pallasli.study.cache.redis.spring.RedisClientTemplate;

public class RedisTempleteClientTest {

	@Test
	public void test() {

		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/spring/applicationContext-redis.xml");
		RedisClientTemplate redisClientTemplete = (RedisClientTemplate) ac.getBean("redisClientTemplate");
		redisClientTemplete.set("tmp", "sd");
		System.out.println(redisClientTemplete.getKeyTag("tmp"));
		System.out.println(redisClientTemplete.get("tmp"));

	}
}
