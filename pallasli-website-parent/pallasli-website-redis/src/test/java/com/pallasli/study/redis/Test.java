package com.pallasli.study.redis;

import java.util.Set;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		System.out.println("server is running" + jedis.ping());
		jedis.set("tmp", "tmpvalue");
		System.out.println("cache:" + jedis.get("tmp"));
		jedis.lpush("tmpList", "v1", "v2");
		jedis.lpush("tmpList", "v3");
		jedis.lpush("tmpList", "v4");
		System.out.println("tmpList:" + jedis.lrange("tmpList", 0, 4).get(4));
		Set<String> l = jedis.keys("*");
		System.out.println("stored keys:" + l.iterator().next());

	}

}
