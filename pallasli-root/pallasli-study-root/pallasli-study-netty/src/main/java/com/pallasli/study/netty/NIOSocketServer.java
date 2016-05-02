package com.pallasli.study.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOSocketServer extends Thread {
	private static final Logger LOG = LoggerFactory
			.getLogger(NIOSocketServer.class);
	private static final String CHARSET = "UTF-8";
	private static final int BUFFER_SIZE = 1024;
	private static final int FAIL_TRY_NUM = 3;

	private Selector selector;
	private ServerSocketChannel ssc;
	private static NIOSocketServer server;

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		server = new NIOSocketServer();
		try {
			// server.setDaemon(true);
			server.initServer();
			server.start();
		} catch (Exception e) {
			// 如果出现异常，则直接关闭客户端
			server.stopServer();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		int failNum = 0;
		while (true) {
			try {
				int select = selector.select();
				if (select > 0) {
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> iter = keys.iterator();
					while (iter.hasNext()) {
						SelectionKey key = iter.next();
						if (key.isAcceptable()) {
							doAcceptable(key);
						}
						if (key.isWritable()) {
							doWriteMessage(key);
						}
						if (key.isReadable()) {
							doReadMessage(key);
						}
						if (key.isConnectable()) {
							doConnectable(key);
						}
						iter.remove();
					}
				}
			} catch (Exception e) {
				failNum++;
				if (failNum > FAIL_TRY_NUM) {
					server.stopServer();
				}
			}
		}

	}

	/**
	 * 初始化服务器端程序，开始监听端口
	 * 
	 * @throws IOException
	 */
	private void initServer() throws IOException {
		selector = Selector.open();
		ssc = ServerSocketChannel.open();
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(2181));
		ssc.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * 停止服务器
	 * 
	 * @throws IOException
	 */
	private void stopServer() {
		try {
			if (selector != null && selector.isOpen()) {
				selector.close();
			}
			if (ssc != null && ssc.isOpen()) {
				ssc.close();
			}
		} catch (IOException e) {
			LOG.info("关闭服务端失败:" + e.getMessage());
		}
	}

	/**
	 * 对新的客户端连接进行处理
	 * 
	 * @param key
	 * @throws IOException
	 */
	private void doAcceptable(SelectionKey key) throws IOException {
		ServerSocketChannel tmpSsc = (ServerSocketChannel) key.channel();
		SocketChannel ss = tmpSsc.accept();
		ss.configureBlocking(false);
		ss.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);

	}

	/**
	 * 已连接
	 * 
	 * @param key
	 */
	private void doConnectable(SelectionKey key) {
		LOG.info("connect is ok");
	}

	/**
	 * 写消息到客户端
	 * 
	 * @param key
	 * @throws IOException
	 */
	private void doWriteMessage(SelectionKey key) throws Exception {
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer buffer = ByteBuffer.wrap("server write msg to client"
				.getBytes(CHARSET));
		while (buffer.hasRemaining()) {
			sc.write(buffer);
		}
		TimeUnit.SECONDS.sleep(1);
	}

	/**
	 * @param key
	 * @throws IOException
	 */
	private void doReadMessage(SelectionKey key) throws Exception {
		SocketChannel sc = (SocketChannel) key.channel();
		ByteBuffer bb = ByteBuffer.allocate(BUFFER_SIZE);
		int read = sc.read(bb);
		while (read > 0) {
			bb.flip();
			byte[] barr = new byte[bb.limit()];
			bb.get(barr);
			LOG.info("server read msg from client:" + new String(barr, CHARSET));
			bb.clear();
			read = sc.read(bb);
		}
		TimeUnit.SECONDS.sleep(1);
	}

}