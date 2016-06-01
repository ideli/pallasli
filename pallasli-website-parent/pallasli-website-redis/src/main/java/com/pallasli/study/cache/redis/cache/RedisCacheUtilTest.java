package com.pallasli.study.cache.redis.cache;

import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.study.cache.redis.spring.User;

public class RedisCacheUtilTest {
	@Autowired
	private RedisCacheUtil<User> redisCache;

	// @RequestMapping("testGetCache")
	public void testGetCache() {
		/*
		 * Map<String,Country> countryMap =
		 * redisCacheUtil1.getCacheMap("country"); Map<String,City> cityMap =
		 * redisCacheUtil.getCacheMap("city");
		 */
		// Map<Integer, Country> countryMap =
		// redisCacheUtil1.getCacheIntegerMap("countryMap");
		// Map<Integer, City> cityMap =
		// redisCacheUtil.getCacheIntegerMap("cityMap");
		//
		// for (int key : countryMap.keySet()) {
		// System.out.println("key = " + key + ",value=" + countryMap.get(key));
		// }
		//
		// System.out.println("------------city");
		// for (int key : cityMap.keySet()) {
		// System.out.println("key = " + key + ",value=" + cityMap.get(key));
		// }
	}
}
