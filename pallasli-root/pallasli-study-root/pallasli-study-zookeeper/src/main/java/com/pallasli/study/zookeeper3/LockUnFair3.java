package com.pallasli.study.zookeeper3;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 分布式助手Zookeeper（七）
 * 
 * 
 * 
 * 上篇文章，散仙介绍了，分布式环境下，基于zookeeper实现的公平的锁，这篇，我们来看下，如何使用zookeeper来完成非公平锁的模拟，在这之前，
 * 我们先来，了解下公平锁和非公平锁的区别。
 * 
 * JAVA JDK提供了公平锁，与非公平锁，但这种实现是基于同一个JVM来说的，
 * 如果同一台机器上，不同的JVM，则可以使用文件锁，来实现，但是这些并不是分布式的模式，虽然可以通过RMI的方式来实现，
 * 但比较繁琐。在分布式的场景里，我们可以轻松的使用zookeeper来实现公平锁与非公平锁
 * 
 * 基于zookeeper实现的公平锁与非公平锁的区别
 * 
 * 先来通俗的看下二者的区别
 * 
 * 公平锁，即先来者先得，只有一个厕所的卫生间，想进去只能是按排队顺序来的，比较公平，first挂掉或释放后，会由secend得到锁，依次类推。
 * 
 * 非公平锁，比较暴力，只有一个厕所的卫生间，不用排队，外面围了一堆人等着上厕所，当里面的人出来时，外面的人谁强势，而且力气大，谁就能进去，
 * 极端情况下，如果两个人一样力气大，这时候就该厕所门发挥作用了，一次只能挤进去一个人，反映到我们的程序中，这时候就需要代码同步了，保证
 * 任何时候，只有一个人可以拿到锁。
 * 
 * 二者的相同点，都保证了，任何情况下，都只能一个人得到某种资源。但实现的方式不同。
 * 
 * 
 * 实现简述：分布式非公平锁的创建，除了得到锁外，其他的多个监听器，监听同一个锁的情况
 * 
 * 实现的流程步骤如下： 序号 介绍 1 创建一个持久znode 2
 * 多个程序并发的去zk服务上，创建同一个短暂无时序性的节点路径，当一个程序，得到锁时，其他程序，只能监听，不能再次创建，创建时需要同步策略 3
 * 同一时刻只能有一个创建成功者，能得到锁 4 没成功者，统一监视得到锁的节点 5 如果中间得到锁的节点，释放了，或者出意外挂掉了，则重复步骤1,2,3,4
 * 
 * @author lyt1987
 *
 */
public class LockUnFair3 implements Watcher {

	/**
	 * ZK实例
	 */
	private ZooKeeper zk;

	/** 原子计数锁，防止在zk没有连上前，执行CURD操作 */
	private CountDownLatch down = new CountDownLatch(1);

	public LockUnFair3() {
		// TODO Auto-generated constructor stub
	}

	public LockUnFair3(String host) throws Exception {
		this.zk = new ZooKeeper(host, 5000, new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				// TODO Auto-generated method stub
				/** 链接上zk服务，岂可取消阻塞计数 **/
				if (event.getState() == KeeperState.SyncConnected) {
					down.countDown();
				}

			}
		});
	}

	/**
	 * 字符编码
	 * 
	 **/
	private static final Charset CHARSET = StandardCharsets.UTF_8;

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub

		if (event.getType() == Event.EventType.NodeDeleted) {

			// 如果发现，监听的节点，挂掉了，那么就重新，进行监听
			try {
				// System.out.println("注意有锁退出或释放，公平锁开始抢占........");
				System.out.println("3我可以去抢占了");
				createTemp();
				// check();
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
	}

	SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 关闭zk连接
	 * 
	 **/
	public void close() throws Exception {
		zk.close();
	}

	Random random = new Random();

	/***
	 * 创建锁node，注意是抢占 的
	 * 
	 * 
	 */
	public void createTemp() throws Exception {

		Thread.sleep(random.nextInt(2500));// 加个线程休眠，实现模拟同步功能

		if (zk.exists("/a/b", this) != null) {
			System.out.println("锁被占用,监听进行中......");
		} else {

			String data = zk.create("/a/b", "a".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			System.out.println("Lock3创建锁成功，节点路径:    " + data);

		}

		// System.out.println("2"+data);

	}

	public static void main(String[] args) throws Exception {

		// Slave s=new Slave("192.168.120.128:2181");
		LockUnFair3 lock = new LockUnFair3("192.168.120.128:2181");
		// lock.createPersist();//创建主节点
		lock.createTemp();
		// lock.check();
		Thread.sleep(Long.MAX_VALUE);
		lock.close();

	}
	/**
	 * 以上是实现的代码，需要注意的是，在最后抢占锁时，可能会一下多个节点同时去建立名字一样的节点，由于zookeeper的特点，只能由一个建立成功，
	 * 其他的会抛出异常，为了避免这种情况，散仙，目前的想到的是，在创建一个节点时，通过线程随机休眠，来达到一个同步情况，但这扔有极端情况，虽然几率很小，
	 * 就是分布式环境下可能有多个节点随机休眠的时间是一样的，所以第二种做法，可以在zk节点维持一个有序的分布式队列，每次只能第一个得到锁，其他的继续等待
	 * ，下一次的抢占，如此一来，就能保证任何时刻只有一个节点得到锁。
	 * 
	 */
}
