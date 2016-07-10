package com.pallali.study.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class LoadingCacheTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		// 缓存接口这里是LoadingCache，LoadingCache在缓存项不存在时可以自动加载缓存
		LoadingCache<Integer, Student> studentCache
		// CacheBuilder的构造函数是私有的，只能通过其静态方法newBuilder()来获得CacheBuilder的实例
		= CacheBuilder.newBuilder()
				// 设置并发级别为8，并发级别是指可以同时写缓存的线程数
				// .concurrencyLevel(8)
				// // 设置写缓存后8秒钟过期
				// .expireAfterWrite(8, TimeUnit.SECONDS)
				// // 设置缓存容器的初始容量为10
				// .initialCapacity(10)
				// // 设置缓存最大容量为100，超过100之后就会按照LRU最近虽少使用算法来移除缓存项
				// .maximumSize(100)
				// // 设置要统计缓存的命中率
				// .recordStats()
				// // 设置缓存的移除通知
				// .removalListener(new RemovalListener<Object, Object>() {
				// @Override
				// public void onRemoval(
				// RemovalNotification<Object, Object> notification) {
				// System.out.println(notification.getKey()
				// + " was removed, cause is "
				// + notification.getCause());
				// }
				// })
				// build方法中可以指定CacheLoader，在缓存不存在时通过CacheLoader的实现自动加载缓存
				.build(new CacheLoader<Integer, Student>() {
					@Override
					public Student load(Integer key) throws Exception {
						System.out.println("load student " + key);
						Student student = new Student();
						student.setId(key);
						student.setName("name " + key);
						return student;
					}
				});

		System.out.println("begin");
		for (int i = 0; i < 20; i++) {
			// 从缓存中得到数据，由于我们没有设置过缓存，所以需要通过CacheLoader加载缓存数据
			Student student = studentCache.get(i);
			System.out.println(student);
			// 休眠1秒
			TimeUnit.SECONDS.sleep(1);
		}

		System.out.println("cache stats:");
		// 最后打印缓存的命中率等 情况
		System.out.println(studentCache.stats().toString());
	}

	public void study() {
		LoadingCache<String, Student> studentCache = CacheBuilder.newBuilder().expireAfterAccess(5L, TimeUnit.MINUTES) // 5分钟后缓存失效
				.maximumSize(5000L) // 最大缓存5000个对象
				// .softValues() //使用SoftReference对象封装value, 使得内存不足时，自动回收
				// .concurrencyLevel(10) //允许同时最多10个线程并发修改
				// .refreshAfterWrite(5L, TimeUnit.SECONDS) //5秒中后自动刷新
				.removalListener(new StudentRemovalListener()) // 注册缓存对象移除监听器
				.ticker(Ticker.systemTicker()) // 定义缓存对象失效的时间精度为纳秒级
				.build(new CacheLoader<String, Student>() {
					@Override
					public Student load(String key) throws Exception {
						// load a new TradeAccount not exists in cache
						return null;
					}
				});
		String spec = "concurrencyLevel=10,expireAfterAccess=5m,softValues";
		CacheBuilderSpec cacheBuilderSpec = CacheBuilderSpec.parse(spec);
		CacheBuilder<Object, Object> cacheBuilder = CacheBuilder.from(cacheBuilderSpec);
		LoadingCache<String, Student> studentCache2 = cacheBuilder.ticker(Ticker.systemTicker())
				.removalListener(new StudentRemovalListener()).build(new CacheLoader<String, Student>() {
					@Override
					public Student load(String key) throws Exception {
						// load a new TradeAccount not exists in cache
						return null;
					}
				});
	}

}
