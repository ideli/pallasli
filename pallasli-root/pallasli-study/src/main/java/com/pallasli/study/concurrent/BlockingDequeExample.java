package com.pallasli.study.concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 
 * java.util.concurrent 包里的 BlockingDeque 接口表示一个线程安放入和提取实例的双端队列。本小节我将给你演示如何使用
 * BlockingDeque。 BlockingDeque
 * 类是一个双端队列，在不能够插入元素时，它将阻塞住试图插入元素的线程；在不能够抽取元素时，它将阻塞住试图抽取的线程。 deque(双端队列) 是
 * "Double Ended Queue" 的缩写。因此，双端队列是一个你可以从任意一端插入或者抽取元素的队列。 BlockingDeque 的使用
 * 在线程既是一个队列的生产者又是这个队列的消费者的时候可以使用到
 * BlockingDeque。如果生产者线程需要在队列的两端都可以插入数据，消费者线程需要在队列的两端都可以移除数据，这个时候也可以使用
 * BlockingDeque。BlockingDeque 图解：
 * 
 * blocking-deque 一个 BlockingDeque - 线程在双端队列的两端都可以插入和提取元素。
 * 一个线程生产元素，并把它们插入到队列的任意一端。如果双端队列已满，插入线程将被阻塞，直到一个移除线程从该队列中移出了一个元素。如果双端队列为空，
 * 移除线程将被阻塞，直到一个插入线程向该队列插入了一个新元素。 BlockingDeque 的方法 BlockingDeque 具有 4
 * 组不同的方法用于插入、移除以及对双端队列中的元素进行检查。如果请求的操作不能得到立即执行的话，每个方法的表现也不同。这些方法如下： 抛异常 特定值 阻塞
 * 超时 插入 addFirst(o) offerFirst(o) putFirst(o) offerFirst(o, timeout, timeunit)
 * 移除 removeFirst(o) pollFirst(o) takeFirst(o) pollFirst(timeout, timeunit) 检查
 * getFirst(o) peekFirst(o)
 * 
 * 
 * 
 * 抛异常 特定值 阻塞 超时 插入 addLast(o) offerLast(o) putLast(o) offerLast(o, timeout,
 * timeunit) 移除 removeLast(o) pollLast(o) takeLast(o) pollLast(timeout,
 * timeunit) 检查 getLast(o) peekLast(o)
 * 
 * 四组不同的行为方式解释： 抛异常：如果试图的操作无法立即执行，抛一个异常。 特定值：如果试图的操作无法立即执行，返回一个特定的值(常常是 true /
 * false)。 阻塞：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行。
 * 超时：如果试图的操作无法立即执行，该方法调用将会发生阻塞，直到能够执行，但等待时间不会超过给定值。返回一个特定值以告知该操作是否成功(典型的是 true
 * / false)。 BlockingDeque 继承自 BlockingQueue BlockingDeque 接口继承自 BlockingQueue
 * 接口。这就意味着你可以像使用一个 BlockingQueue 那样使用
 * BlockingDeque。如果你这么干的话，各种插入方法将会把新元素添加到双端队列的尾端，而移除方法将会把双端队列的首端的元素移除。正如
 * BlockingQueue 接口的插入和移除方法一样。 以下是 BlockingDeque 对 BlockingQueue 接口的方法的具体内部实现：
 * BlockingQueue BlockingDeque add() addLast() offer() x 2 offerLast() x 2 put()
 * putLast()
 * 
 * remove() removeFirst() poll() x 2 pollFirst() take() takeFirst()
 * 
 * element() getFirst() peek() peekFirst()
 * 
 * BlockingDeque 的实现 既然 BlockingDeque
 * 是一个接口，那么你想要使用它的话就得使用它的众多的实现类的其中一个。java.util.concurrent 包提供了以下 BlockingDeque
 * 接口的实现类： LinkedBlockingDeque
 * 
 * 
 * 链阻塞双端队列 LinkedBlockingDeque LinkedBlockingDeque 类实现了 BlockingDeque 接口。
 * deque(双端队列) 是 "Double Ended Queue"
 * 的缩写。因此，双端队列是一个你可以从任意一端插入或者抽取元素的队列。(译者注：唐僧啊，受不了。) LinkedBlockingDeque
 * 是一个双端队列，在它为空的时候，一个试图从中抽取数据的线程将会阻塞，无论该线程是试图从哪一端抽取数据。
 * 
 * @author lyt1987
 *
 */
public class BlockingDequeExample {
	public static void main(String[] args) throws InterruptedException {
		BlockingDeque<String> deque = new LinkedBlockingDeque<String>();

		deque.addFirst("1");
		deque.addLast("2");

		String two = deque.takeLast();
		String one = deque.takeFirst();
		System.out.println(one);
		System.out.println(two);
	}
}
