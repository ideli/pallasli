package com.pallasli.study.zookeeper;

/**
 * A simple example program to use DataMonitor to start and
 * stop executables based on a znode. The program watches the
 * specified znode and saves the data that corresponds to the
 * znode in the filesystem. It also starts the specified program
 * with the specified arguments when the znode exists and kills
 * the program if the znode goes away.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * 一个简单的 Zookeeper Watch 客户端
 * 
 * 为了介绍 Zookeeper Java API 的基本用法，本文将带你如何一步一步实现一个功能简单的 Zookeeper 客户端。该 Zookeeper
 * 客户端会监视一个你指定 Zookeeper 节点 Znode， 当被监视的节点发生变化时，客户端会启动或者停止某一程序。
 * 
 * 基本要求
 * 
 * 该客户端具备四个基本要求：
 * 
 * 客户端所带参数： Zookeeper 服务地址。 被监视的 Znode 节点名称。 可执行程序及其所带的参数 客户端会获取被监视 Znode
 * 节点的数据并启动你所指定的可执行程序。 如果被监视的 Znode 节点发生改变，客户端重新获取其内容并再次启动你所指定的可执行程序。 如果被监视的
 * Znode 节点消失，客户端会杀死可执行程序。 程序设计
 * 
 * 一般而言，Zookeeper 应用程序分为两部分，其中一部分维护与服务器端的连接，另外一部分监视 Znode 节点的数据。在本程序中，Executor
 * 类负责维护 Zookeeper 连接，DataMonitor 类监视 Zookeeper 目录树中的数据， 同时，Executor
 * 包含了主线程和程序主要的执行逻辑，它负责少量的用户交互，以及与可执行程序的交互，该可执行程序接受你向它传入的参数，并且会根据被监视的 Znode
 * 节点的状态变化停止或重启。
 * 
 * Executor类
 * 
 * Executor 对象是本例程最基本的“容器”，它包括Zookeeper 对象和DataMonitor对象。
 * 
 * 回忆一下 Executor 的任务是根据 Zookeeper 中 Znode 节点状态改变所触发的事件来启动和停止你在命令行指定的可执行程序，
 * 在上面的代码你可以看到，Executor 类在其构造函数中实例化 Zookeeper 对象时，将其自身的引用作为 Watch 参数传递给
 * Zookeeper 的构造函数，同时它也将其自身的引用作为 DataMonitorListener 参数传递给 DataMonitor
 * 的构造函数。Executor 本身实现了以下接口：
 * 
 * public class Executor implements Watcher, Runnable,
 * DataMonitor.DataMonitorListener { ... Watcher 接口是在ZooKeeper Java API 中定义的。
 * ZooKeeper 用它来与“容器”（此处“容器”与上面的 Executor 类相似）进行通信，Watcher 只支持一个方法，即process(),
 * ZooKeeper 用该函数来处理主线程可能感兴趣的事件，例如 Zookeeper 连接或会话的状态，本例中的“容器”
 * Executor只是简单地把事件向下传递给 DataMonitor，具体如何处理事件是由 DataMonitor 决定的。本文只是简单地描述了如何使用
 * Watcher，通常情况下，Executor 或 与 Executor 类似的对象拥有 与Zookeeper
 * 服务端的连接，但它可以将事件传递给其他对象，并有其它的对象处理该事件。
 * 
 * public void process(WatchedEvent event) { dm.process(event); }
 * 
 * 
 * 
 * 
 * DataMonitorListener 接口本身不是Zookeeper API
 * 的一部分，它完全是一个自定义的接口，可以说是专门为本程序设计的。DataMonitor 对象使用该接口和“容器”（即 Executor
 * 类）进行通信，DataMonitorListener 接口如下： 该接口在 DataMonitor 中定义，Executor 类实现该接口，当
 * Executor.exists() 被调用的时候，Executor 决定是否启动或停止事先指定的应用程序（回忆一下前文所说的，当 Znode 消失时
 * Zookeeper 客户端会杀死该可执行程序）。
 * 
 * 当 Executor.closing() 被调用的时候，Executor 会根据 Zookeeper 连接永久性地消失来决定是否关闭自己。
 * 
 * 你或许已经猜到，DataMonitor 对象根据 Zookeeper 状态变化来调用这些方法吧？
 * 
 * 以下是 Executor 类中实现 DataMonitorListener.exists() 和
 * DataMonitorListener.closing()的代码：
 * 
 * @author lyt1987
 *
 */
public class Executor implements Watcher, Runnable, DataMonitor.DataMonitorListener {
	String znode;

	DataMonitor dm;

	ZooKeeper zk;

	String filename;

	String exec[];

	Process child;

	public Executor(String hostPort, String znode, String filename, String exec[]) throws KeeperException, IOException {
		this.filename = filename;
		this.exec = exec;
		zk = new ZooKeeper(hostPort, 3000, this);
		dm = new DataMonitor(zk, znode, null, this);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		args = new String[] { "127.0.0.1", "1", "filetxt", "" };
		if (args.length < 4) {
			System.err.println("USAGE: Executor hostPort znode filename program [args ...]");
			System.exit(2);
		}
		String hostPort = args[0];
		String znode = args[1];
		String filename = args[2];
		String exec[] = new String[args.length - 3];
		System.arraycopy(args, 3, exec, 0, exec.length);
		try {
			new Executor(hostPort, znode, filename, exec).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***************************************************************************
	 * We do process any events ourselves, we just need to forward them on.
	 * 
	 * @see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.proto.WatcherEvent)
	 */
	@Override
	public void process(WatchedEvent event) {
		dm.process(event);
	}

	@Override
	public void run() {
		try {
			synchronized (this) {
				while (!dm.dead) {
					wait();
				}
			}
		} catch (InterruptedException e) {
		}
	}

	@Override
	public void closing(int rc) {
		synchronized (this) {
			notifyAll();
		}
	}

	static class StreamWriter extends Thread {
		OutputStream os;

		InputStream is;

		StreamWriter(InputStream is, OutputStream os) {
			this.is = is;
			this.os = os;
			start();
		}

		@Override
		public void run() {
			byte b[] = new byte[80];
			int rc;
			try {
				while ((rc = is.read(b)) > 0) {
					os.write(b, 0, rc);
				}
			} catch (IOException e) {
			}

		}
	}

	@Override
	public void exists(byte[] data) {
		if (data == null) {
			if (child != null) {
				System.out.println("Killing process");
				child.destroy();
				try {
					child.waitFor();
				} catch (InterruptedException e) {
				}
			}
			child = null;
		} else {
			if (child != null) {
				System.out.println("Stopping child");
				child.destroy();
				try {
					child.waitFor();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(filename);
				fos.write(data);
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				System.out.println("Starting child");
				child = Runtime.getRuntime().exec(exec);
				new StreamWriter(child.getInputStream(), System.out);
				new StreamWriter(child.getErrorStream(), System.err);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}