package com.pallasli.study.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 
 * 1. java.util.concurrent - Java 并发工具包 Java 5 添加了一个新的包到 Java
 * 平台，java.util.concurrent 包。这个包包含有一系列能够让 Java
 * 的并发编程变得更加简单轻松的类。在这个包被添加以前，你需要自己去动手实现自己的相关工具类。 本文我将带你一一认识 java.util.concurrent
 * 包里的这些类，然后你可以尝试着如何在项目中使用它们。本文中我将使用 Java 6 版本，我不确定这和 Java 5 版本里的是否有一些差异。
 * 我不会去解释关于 Java 并发的核心问题 - 其背后的原理，也就是说，如果你对那些东西感兴趣，参考《Java 并发指南》。 半成品 本文很大程度上还是个
 * "半成品"，所以当你发现一些被漏掉的类或接口时，请耐心等待。在我空闲的时候会把它们加进来的。
 * 
 * 2. 阻塞队列 BlockingQueue java.util.concurrent 包里的 BlockingQueue
 * 接口表示一个线程安放入和提取实例的队列。本小节我将给你演示如何使用这个 BlockingQueue。 本节不会讨论如何在 Java 中实现一个你自己的
 * BlockingQueue。如果你对那个感兴趣，参考《Java 并发指南》 BlockingQueue 用法 BlockingQueue
 * 通常用于一个线程生产对象，而另外一个线程消费这些对象的场景。下图是对这个原理的阐述：
 * 
 * blocking-queue 一个线程往里边放，另外一个线程从里边取的一个 BlockingQueue。
 * 一个线程将会持续生产新对象并将其插入到队列之中，直到队列达到它所能容纳的临界点。也就是说，它是有限的。如果该阻塞队列到达了其临界点，
 * 负责生产的线程将会在往里边插入新对象时发生阻塞。它会一直处于阻塞之中，直到负责消费的线程从队列中拿走一个对象。
 * 负责消费的线程将会一直从该阻塞队列中拿出对象。如果消费线程尝试去从一个空的队列中提取对象的话，这个消费线程将会处于阻塞之中，
 * 直到一个生产线程把一个对象丢进队列。 BlockingQueue 的方法 BlockingQueue 具有 4
 * 组不同的方法用于插入、移除以及对队列中的元素进行检查。如果请求的操作不能得到立即执行的话，每个方法的表现也不同。这些方法如下： 抛异常 特定值 阻塞 超时
 * 插入 add(o) offer(o) put(o) offer(o, timeout, timeunit) 移除 remove(o) poll(o)
 * take(o) poll(timeout, timeunit) 检查 element(o) peek(o)
 * 
 * 四组不同的行为方式解释： 抛异常：如果试图的操作无法立即执行，抛一个异常。 特定值：如果试图的操作无法立即执行，返回一个特定的值(常常是 true /
 * false)。 阻塞：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行。
 * 超时：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行，但等待时间不会超过给定值。返回一个特定值以告知该操作是否成功(典型的是 true
 * / false)。 无法向一个 BlockingQueue 中插入 null。如果你试图插入 null，BlockingQueue 将会抛出一个
 * NullPointerException。 可以访问到 BlockingQueue
 * 中的所有元素，而不仅仅是开始和结束的元素。比如说，你将一个对象放入队列之中以等待处理，但你的应用想要将其取消掉。那么你可以调用诸如 remove(o)
 * 方法来将队列之中的特定对象进行移除。但是这么干效率并不高(译者注：基于队列的数据结构，获取除开始或结束位置的其他对象的效率不会太高)，
 * 因此你尽量不要用这一类的方法，除非你确实不得不那么做。 BlockingQueue 的实现 BlockingQueue
 * 是个接口，你需要使用它的实现之一来使用 BlockingQueue。java.util.concurrent 具有以下 BlockingQueue
 * 接口的实现(Java 6)： ArrayBlockingQueue DelayQueue LinkedBlockingQueue
 * PriorityBlockingQueue SynchronousQueue
 * 
 * 
 * Java 中使用 BlockingQueue 的例子 这里是一个 Java 中使用 BlockingQueue 的示例。本示例使用的是
 * BlockingQueue 接口的 ArrayBlockingQueue 实现。 首先，BlockingQueueExample
 * 类分别在两个独立的线程中启动了一个 Producer 和 一个 Consumer。Producer 向一个共享的 BlockingQueue
 * 中注入字符串，而 Consumer 则会从中把它们拿出来。
 * 
 * 注意它在每次 put() 调用时是如何休眠一秒钟的。这将导致 Consumer 在等待队列中对象的时候发生阻塞。
 * 
 * @author lyt1987
 *
 */
public class BlockingQueueExample {
	public static void main(String[] args) throws Exception {

		/**
		 * ArrayBlockingQueue 类实现了 BlockingQueue 接口。
		 * 
		 * ArrayBlockingQueue是一个有界的阻塞队列，其内部实现是将对象放到一个数组里。有界也就意味着，它不能够存储无限多数量的元素。
		 * 它有一个同一时间能够存储元素数量的上限。你可以在对其初始化的时候设定这个上限，但之后就无法对这个上限进行修改了(译者注：
		 * 因为它是基于数组实现的，也就具有数组的特性：一旦初始化，大小就无法修改)。
		 * 
		 * ArrayBlockingQueue 内部以
		 * FIFO(先进先出)的顺序对元素进行存储。队列中的头元素在所有元素之中是放入时间最久的那个，而尾元素则是最短的那个。
		 * 
		 * BlockingQueue<String> queue = new ArrayBlockingQueue<String>(1024);
		 * 
		 * queue.put("1");
		 * 
		 * String string = queue.take();
		 */
		BlockingQueue queue = new ArrayBlockingQueue(1024);

		Producer producer = new Producer(queue);
		Consumer consumer = new Consumer(queue);

		new Thread(producer).start();
		new Thread(consumer).start();

		Thread.sleep(4000);
	}
}
