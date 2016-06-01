package com.pallasli.website.cache.redis.app;

import org.junit.Test;

/**
 * RedisApp的测试类
 *
 * @author yangzhilong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisAppTest {

	@Test
	public void crudFromRedisWidthSimpleTest() {
		RedisApp app = new RedisApp();
		app.crudFromRedisWidthSimple();
	}

	@Test
	public void crudFromRedisWidthPoolTest() {
		RedisApp app = new RedisApp();
		app.crudFromRedisWidthPool();
	}
}