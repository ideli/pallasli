package com.pallasli.study.cache.redis.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Service
public class RedisService {

    @Autowired(required = false)
    private ShardedJedisPool shardedJedisPool;

    private <T> T execute(Function<T, ShardedJedis> fun) {
        ShardedJedis shardedJedis = null;
        try {
            shardedJedis = shardedJedisPool.getResource();
            return fun.callback(shardedJedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }

    /**
     * 保存数据到redis中
     * 
     * @param key
     * @param value
     * @return
     */
    public String set(final String key, final String value) {
        return execute(new Function<String, ShardedJedis>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.set(key, value);
            }
        });
    }

    /**
     * 从redis中获取数据
     * 
     * @param key
     * @param value
     * @return
     */
    public String get(final String key) {
        return execute(new Function<String, ShardedJedis>() {
            @Override
            public String callback(ShardedJedis shardedJedis) {
                return shardedJedis.get(key);
            }
        });
    }

    /**
     * 从redis中获取数据
     * 
     * @param key
     * @param value
     * @return
     */
    public Long del(final String key) {
        return execute(new Function<Long, ShardedJedis>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.del(key);
            }
        });
    }

    /**
     * 保存数据到redis中,并设置生存时间
     * 
     * @param key
     * @param value
     * @param time 生存时间，单位是秒
     * @return
     */
    public Long set(final String key, final String value, final Integer time) {
        return execute(new Function<Long, ShardedJedis>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                shardedJedis.set(key, value);
                return shardedJedis.expire(key, time);
            }
        });
    }

    /**
     * 根据key，设置数据的生存时间
     * 
     * @param key
     * @param value
     * @param time
     * @return
     */
    public Long set(final String key, final Integer time) {
        return execute(new Function<Long, ShardedJedis>() {
            @Override
            public Long callback(ShardedJedis shardedJedis) {
                return shardedJedis.expire(key, time);
            }
        });
    }

}
