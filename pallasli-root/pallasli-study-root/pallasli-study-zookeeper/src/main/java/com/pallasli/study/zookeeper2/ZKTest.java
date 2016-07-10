package com.pallasli.study.zookeeper2;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZKTest {

	static ZooKeeper zk = null;

	static CountDownLatch cdl = new CountDownLatch(1);

	static {

		try {

			zk = new ZooKeeper("127.0.0.1:2181", 1000, new MyWatcher(cdl));

		} catch (IOException e) {

			e.printStackTrace();

		}

	}

	public static void main(String[] args) {

		try {

			cdl.await();

		} catch (InterruptedException e1) {

			e1.printStackTrace();

		}

		try {

			zk.create("/s", "test".getBytes(), Ids.OPEN_ACL_UNSAFE,

					CreateMode.PERSISTENT);

		} catch (KeeperException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (InterruptedException e) {

			e.printStackTrace();

		}

	}

}

class MyWatcher implements Watcher {

	private CountDownLatch countDownLatch;

	public MyWatcher(CountDownLatch countDownLatch) {

		this.countDownLatch = countDownLatch;

	}

	@Override
	public void process(WatchedEvent event) {

		if (event.getState() == KeeperState.SyncConnected) {

			System.err.println("连接上");

			countDownLatch.countDown();

		}

	}

}
