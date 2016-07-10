package com.pallasli.study.apachecommons.dbutils;

/**
 * 使用ThreadLocal进行更加优雅的事务处理
 * 
 * 上面的在businessService层这种处理事务的方式依然不够优雅，为了能够让事务处理更加优雅，我们使用ThreadLocal类进行改造，
 * ThreadLocal一个容器，向这个容器存储的对象，在当前线程范围内都可以取得出来，向ThreadLocal里面存东西就是向它里面的Map存东西的，
 * 然后ThreadLocal把这个Map挂到当前的线程底下，这样Map就只属于这个线程了
 * 
 * 
 * @author lyt1987
 *
 */
public class ThreadLocalTest {

	public static void main(String[] args) {
		// 得到程序运行时的当前线程
		Thread currentThread = Thread.currentThread();
		System.out.println(currentThread);
		// ThreadLocal一个容器，向这个容器存储的对象，在当前线程范围内都可以取得出来
		ThreadLocal<String> t = new ThreadLocal<String>();
		// 把某个对象绑定到当前线程上 对象以键值对的形式存储到一个Map集合中，对象的的key是当前的线程，如：
		// map(currentThread,"aaa")
		t.set("aaa");
		// 获取绑定到当前线程中的对象
		String value = t.get();
		// 输出value的值是aaa
		System.out.println(value);
	}
}