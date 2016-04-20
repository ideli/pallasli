package com.pallasli.study.cache.memcahed;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;

import net.spy.memcached.MemcachedClient;

public class SpyMemcachedTest {
	public static void main(String[] args) {
		try {
			/* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */
			MemcachedClient mc = new MemcachedClient(new InetSocketAddress(
					"192.168.1.20", 12111));
			Future<Boolean> b = null;
			/* 将key值，过期时间(秒)和要缓存的对象set到memcached中 */
			b = mc.set("neea:testDaF:ksIdno", 900, "someObject");
			if (b.get().booleanValue() == true) {
				mc.shutdown();
			} /* 按照key值从memcached中查找缓存，不存在则返回null */

			mc = new MemcachedClient(new InetSocketAddress("192.168.1.20",
					12111));
			Object c = mc.get("neea:testDaF:ksIdno ");
			mc.shutdown();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
