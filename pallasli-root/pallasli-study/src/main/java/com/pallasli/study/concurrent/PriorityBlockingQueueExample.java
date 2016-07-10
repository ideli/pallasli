package com.pallasli.study.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 具有优先级的阻塞队列 PriorityBlockingQueue PriorityBlockingQueue 类实现了 BlockingQueue 接口。
 * PriorityBlockingQueue 是一个无界的并发队列。它使用了和类 java.util.PriorityQueue
 * 一样的排序规则。你无法向这个队列中插入 null 值。 所有插入到 PriorityBlockingQueue 的元素必须实现
 * java.lang.Comparable 接口。因此该队列中元素的排序就取决于你自己的 Comparable 实现。 注意
 * PriorityBlockingQueue 对于具有相等优先级(compare() == 0)的元素并不强制任何特定行为。 同时注意，如果你从一个
 * PriorityBlockingQueue 获得一个 Iterator 的话，该 Iterator 并不能保证它对元素的遍历是以优先级为序的。
 * 
 * @author lyt1987
 *
 */
public class PriorityBlockingQueueExample {
	/**
	 * 同步队列 SynchronousQueue SynchronousQueue 类实现了 BlockingQueue 接口。
	 * SynchronousQueue
	 * 是一个特殊的队列，它的内部同时只能够容纳单个元素。如果该队列已有一元素的话，试图向队列中插入一个新元素的线程将会阻塞，
	 * 直到另一个线程将该元素从队列中抽走。同样，如果该队列为空，试图向队列中抽取一个元素的线程将会阻塞，直到另一个线程向队列中插入了一条新的元素。
	 * 据此，把这个类称作一个队列显然是夸大其词了。它更多像是一个汇合点。
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue queue = new PriorityBlockingQueue();

		// String implements java.lang.Comparable
		queue.put("doyb");
		queue.put("Value2");
		queue.put("Value1");
		queue.put("con");

		String value = (String) queue.take();
		System.out.println(value);
		value = (String) queue.take();
		System.out.println(value);
		value = (String) queue.take();
		System.out.println(value);
		value = (String) queue.take();
		System.out.println(value);
	}
}
