package com.pallasli.study.netty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOSocketClient extends Thread {
	private static final Logger LOG = LoggerFactory
			.getLogger(NIOSocketClient.class);
	private static final String CHARSET = "UTF-8";
	private static final int BUFFER_SIZE = 1024;
	private static final int FAIL_TRY_NUM = 3;

	private SocketChannel socketChannel;
	private Selector selector;
	private static NIOSocketClient client;

	/**
	 * 程序入口
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		client = new NIOSocketClient();
		try {
			client.initClient();
			client.start();
			// client.setDaemon(true);
		} catch (Exception e) {
			// 如果出现异常，则直接关闭客户端
			client.close();
		}
	}

	@Override
	public void run() {
		int failNum = 0;
		while (true) {
			try {
				writeMessage();
				int select = selector.select();
				System.out.println(select);
				if (select > 0) {
					Set<SelectionKey> keys = selector.selectedKeys();
					Iterator<SelectionKey> iter = keys.iterator();
					while (iter.hasNext()) {
						SelectionKey sk = iter.next();
						if (sk.isReadable()) {
							readMessage(sk);
						}
						iter.remove();
					}
				}
			} catch (Exception e) {
				// 如果出现三次以上的异常，则关闭客户端
				failNum++;
				if (failNum > FAIL_TRY_NUM) {
					client.close();
					System.exit(1);
				}
			}
		}
	}

	public void readMessage(SelectionKey sk) throws Exception,
			UnsupportedEncodingException {
		SocketChannel curSc = (SocketChannel) sk.channel();
		ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
		while (curSc.read(buffer) > 0) {
			buffer.flip();
			LOG.info("read message from server:"
					+ new String(buffer.array(), CHARSET));
			buffer.clear();
		}
		TimeUnit.SECONDS.sleep(1);
	}

	public void writeMessage() throws Exception {
		String ss = "client write msg to server";
		ByteBuffer buffer = ByteBuffer.wrap(ss.getBytes(CHARSET));
		while (buffer.hasRemaining()) {
			socketChannel.write(buffer);
		}

		TimeUnit.SECONDS.sleep(1);
	}

	public void initClient() throws IOException, ClosedChannelException {
		InetSocketAddress addr = new InetSocketAddress(2181);
		socketChannel = SocketChannel.open();

		selector = Selector.open();
		socketChannel.configureBlocking(false);
		socketChannel.register(selector, SelectionKey.OP_READ);

		// 连接到server
		socketChannel.connect(addr);

		while (!socketChannel.finishConnect()) {
			LOG.info("check finish connection");
		}
	}

	/**
	 * 停止客户端
	 */
	private void close() {
		try {
			if (selector != null && selector.isOpen()) {
				selector.close();
			}
			if (socketChannel != null && socketChannel.isOpen()) {
				socketChannel.close();
			}
		} catch (IOException e) {
			LOG.info("关闭客户端失败:" + e.getMessage());
		}
	}

}