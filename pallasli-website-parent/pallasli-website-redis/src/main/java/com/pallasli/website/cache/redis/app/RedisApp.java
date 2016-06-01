package com.pallasli.website.cache.redis.app;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Reids之jedis的CRUD操作
 *
 * @author yangzhilong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class RedisApp {
	public Logger log = Logger.getLogger(this.getClass());

	private static JedisPool pool;
	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle("redis-pool");
		if (bundle == null) {
			// 假设直接抛出Exception咋必须进行处理，不然编译不会通过
			throw new IllegalArgumentException("redis-pool.properties file is not found");
		}
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置pool的一些参数,可选,详细配置项参见GenericObjectPoolConfig类
		config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
		config.setMaxWaitMillis(Long.valueOf(bundle.getString("redis.pool.maxWait")));
		config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
		config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));

		// 使用默认配置时可以使用如下方法初始化池
		// pool = new JedisPool(bundle.getString("redis.ip"),
		// Integer.valueOf(bundle.getString("redis.port")));
		pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
	}

	/**
	 * 
	 * 功能描述: <br>
	 * CRUD操作之hello world
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void crudFromRedisWidthSimple() {
		Jedis jedis = new Jedis(bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
		log.info("get connection with simple ");
		crudMethod(jedis);
		// 关闭连接
		jedis.close();
	}

	/**
	 * 
	 * 使用common-pool操作redis
	 *
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	public void crudFromRedisWidthPool() {
		Jedis jedis = pool.getResource();
		log.info("get connection from pool , obect is:" + jedis);
		crudMethod(jedis);
		// 释放链接
		pool.close();
	}

	/**
	 * 
	 * crud基本操作单元
	 *
	 * @param jedis
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	private void crudMethod(Jedis jedis) {
		log.info("insert value to redis~~~");
		jedis.set("name", "hello jedis");

		log.info("get value from redis, value:" + jedis.get("name"));

		log.info("delete key from redis~~~");
		jedis.del("name");

		log.info("get value from redis, value:" + jedis.get("name"));
	}
}