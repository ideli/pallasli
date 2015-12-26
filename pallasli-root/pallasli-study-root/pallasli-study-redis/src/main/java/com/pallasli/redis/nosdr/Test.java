package com.pallasli.redis.nosdr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"classpath:/no-spring-data-redis.xml");
		RedisClientTemplate redisClient = (RedisClientTemplate) ac
				.getBean("redisClientTemplate");
		redisClient.set("a", "abc");
		System.out.println(redisClient.get("a"));
	}

}
