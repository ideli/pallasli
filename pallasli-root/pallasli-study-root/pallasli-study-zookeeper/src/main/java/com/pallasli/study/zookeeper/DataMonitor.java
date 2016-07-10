package com.pallasli.study.zookeeper;

/**
 * A simple class that monitors the data and existence of a ZooKeeper
 * node. It uses asynchronous ZooKeeper APIs.
 */
import java.util.Arrays;

import org.apache.zookeeper.AsyncCallback.StatCallback;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

/**
 * 
 * DataMonitor 类
 * 
 * DataMonitor 类是本程序 Zookeeper 逻辑的核心， 它差不多是异步的，并由事件驱动的。DataMonitor 构造函数如下：
 * 
 * 调用 ZooKeeper.exists() 检查指定的 Znode 是否存在，并设置监视，传递自身引用作为回调对象，在某种意义上，在 watch
 * 触发时就会引起真实的处理流程。
 * 
 * 当 ZooKeeper.exists() 操作在服务器端完成时，ZooKeeper API 会在客户端调用 completion callback：
 * 
 * 上述代码首先检查 Znode 是否存在，以及其他重大的不可恢复的错误。如果文件（或者Znode）存在，它将从 Znode 获取数据，如果状态发生变化再调用
 * Executor 的 exists() 回调函数。注意，getData 函数本省必须要做任何的异常处理，因为本身就有监视可以处理任何错误：如果节点在调用
 * ZooKeeper.getData() 之前被删除，ZooKeeper.exists()
 * 就会触发回调函数，如果存在通信错误，在连接上的监视会在该连接重建之前触发相应的事件，同时引发相应的处理。
 * 
 * 最后，DataMonitor 处理监视事件的代码如下：
 * 
 * 如果客户端 Zookeeper 程序在会话失效时(Expired event)重新建立了通信信道(SyncConnected event)
 * ，所有的会话监视会自动和服务器进行重连， (Zookeeper 3.0.0以上版本会重置之前设置的监视). 更多编程指南请参见 ZooKeeper
 * Watches 。 当 DataMonitor 获得了指定 Znode 的事件后，它将调用 ZooKeeper.exists() 来决定究竟发生了什么。
 * 
 * 
 * 
 * @author lyt1987
 *
 */
public class DataMonitor implements Watcher, StatCallback {

	ZooKeeper zk;

	String znode;

	Watcher chainedWatcher;

	boolean dead;

	DataMonitorListener listener;

	byte prevData[];

	public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher, DataMonitorListener listener) {
		this.zk = zk;
		this.znode = znode;
		this.chainedWatcher = chainedWatcher;
		this.listener = listener;
		// Get things started by checking if the node exists. We are going
		// to be completely event driven
		zk.exists(znode, true, this, null);
	}

	/**
	 * Other classes use the DataMonitor by implementing this method
	 */
	public interface DataMonitorListener {
		/**
		 * The existence status of the node has changed.
		 */
		void exists(byte data[]);

		/**
		 * The ZooKeeper session is no longer valid.
		 * 
		 * @param rc
		 *            the ZooKeeper reason code
		 */
		void closing(int rc);
	}

	@Override
	public void process(WatchedEvent event) {
		String path = event.getPath();
		if (event.getType() == Event.EventType.None) {
			// We are are being told that the state of the
			// connection has changed
			switch (event.getState()) {
			case SyncConnected:
				// In this particular example we don't need to do anything
				// here - watches are automatically re-registered with
				// server and any watches triggered while the client was
				// disconnected will be delivered (in order of course)
				break;
			case Expired:
				// It's all over
				dead = true;
				listener.closing(KeeperException.Code.SessionExpired);
				break;
			}
		} else {
			if (path != null && path.equals(znode)) {
				// Something has changed on the node, let's find out
				zk.exists(znode, true, this, null);
			}
		}
		if (chainedWatcher != null) {
			chainedWatcher.process(event);
		}
	}

	@Override
	public void processResult(int rc, String path, Object ctx, Stat stat) {
		boolean exists;
		switch (rc) {
		case Code.Ok:
			exists = true;
			break;
		case Code.NoNode:
			exists = false;
			break;
		case Code.SessionExpired:
		case Code.NoAuth:
			dead = true;
			listener.closing(rc);
			return;
		default:
			// Retry errors
			zk.exists(znode, true, this, null);
			return;
		}

		byte b[] = null;
		if (exists) {
			try {
				b = zk.getData(znode, false, null);
			} catch (KeeperException e) {
				// We don't need to worry about recovering now. The watch
				// callbacks will kick off any exception handling
				e.printStackTrace();
			} catch (InterruptedException e) {
				return;
			}
		}
		if ((b == null && b != prevData) || (b != null && !Arrays.equals(prevData, b))) {
			listener.exists(b);
			prevData = b;
		}
	}
}
