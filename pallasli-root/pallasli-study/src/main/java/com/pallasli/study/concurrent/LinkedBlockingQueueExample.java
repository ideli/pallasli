package com.pallasli.study.concurrent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 链阻塞队列 LinkedBlockingQueue LinkedBlockingQueue 类实现了 BlockingQueue 接口。
 * LinkedBlockingQueue
 * 内部以一个链式结构(链接节点)对其元素进行存储。如果需要的话，这一链式结构可以选择一个上限。如果没有定义上限，将使用 Integer.MAX_VALUE
 * 作为上限。 LinkedBlockingQueue 内部以
 * FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
 * 
 * @author lyt1987
 *
 */
public class LinkedBlockingQueueExample {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
		BlockingQueue<String> bounded = new LinkedBlockingQueue<String>(1024);

		bounded.put("Value");

		String value = bounded.take();
		System.out.println(value);
	}
}
