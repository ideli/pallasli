package com.pallasli.redis.nosdr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Repository("redisDataSource")
public class RedisDataSourceImpl implements RedisDataSource {

	// private static final Logger log = LoggerFactory
	// .getLogger(RedisDataSourceImpl.class);
	/**
	 * 1, 注入配置好的ShardedJedisPool，这三个方法的作用：
	 * 
	 * getRedisClient() ： 取得redis的客户端，可以执行命令了。 returnResource(ShardedJedis
	 * shardedJedis) ： 将资源返还给pool returnResource(ShardedJedis shardedJedis,
	 * boolean broken) : 出现异常后，将资源返还给pool （其实不需要第二个方法）
	 */
	@Autowired
	private ShardedJedisPool shardedJedisPool;

	public ShardedJedis getRedisClient() {
		try {
			ShardedJedis shardJedis = shardedJedisPool.getResource();
			return shardJedis;
		} catch (Exception e) {
			// log.error("getRedisClent error", e);
			e.printStackTrace();
		}
		return null;
	}

	public void returnResource(ShardedJedis shardedJedis) {
		shardedJedisPool.returnResource(shardedJedis);
	}

	public void returnResource(ShardedJedis shardedJedis, boolean broken) {
		if (broken) {
			shardedJedisPool.returnBrokenResource(shardedJedis);
		} else {
			shardedJedisPool.returnResource(shardedJedis);
		}
	}
}
