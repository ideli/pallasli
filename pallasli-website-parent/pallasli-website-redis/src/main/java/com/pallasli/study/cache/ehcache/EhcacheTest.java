package com.pallasli.study.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.BlockingCache;

public class EhcacheTest {
	public static void main(String[] args) {
		// CacheManager cacheManager = CacheManager.create();
		// 或者
		// CacheManager cacheManager = CacheManager.getInstance();
		// 或者
		CacheManager cacheManager = CacheManager.create("src/main/resources/ehcache/ehcache.xml");
		// 或者
		// CacheManager cacheManager =
		// CacheManager.create("http://localhost:8080/test/ehcache.xml");
		// CacheManager cacheManager =
		// CacheManager.newInstance("src/main/resources/ehcache/ehcache.xml");
		// .......

		// 获取ehcache配置文件中的一个cache
		Cache sample = cacheManager.getCache("sample");
		// 获取页面缓存
		BlockingCache cache = new BlockingCache(cacheManager.getEhcache("SimplePageCachingFilter"));
		// 添加数据到缓存中
		Element element = new Element("key", "val");
		sample.put(element);
		// 获取缓存中的对象，注意添加到cache中对象要序列化 实现Serializable接口
		Element result = sample.get("key");
		// 删除缓存
		sample.remove("key");
		sample.removeAll();

		// 获取缓存管理器中的缓存配置名称
		for (String cacheName : cacheManager.getCacheNames()) {
			System.out.println(cacheName);
		}
		// 获取所有的缓存对象
		for (Object key : cache.getKeys()) {
			System.out.println(key);
		}

		// 得到缓存中的对象数
		System.out.println(cache.getSize());
		// 得到缓存对象占用内存的大小
		System.out.println(cache.getMemoryStoreSize());
		// 得到缓存读取的命中次数
		System.out.println(cache.getStatistics().cacheHitCount());
		// 得到缓存读取的错失次数
		System.out.println(cache.getStatistics().cacheMissCount());

	}
}
