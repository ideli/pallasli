package com.pallasli.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;

public class SocketTest {
	public static void test() throws IOException {
		/**
		 * Http连接使用java.net.Socket类来传输数据。这依赖于ConnectionSocketFactory接口来创建、
		 * 初始化和连接socket。这样也就允许HttpClient的用户在代码运行时，指定socket初始化的代码。
		 * PlainConnectionSocketFactory是默认的创建、初始化明文socket（不加密）的工厂类。
		 * 
		 * 创建socket和使用socket连接到目标主机这两个过程是分离的，所以我们可以在连接发生阻塞时，关闭socket连接。
		 **/
		HttpClientContext clientContext = HttpClientContext.create();
		PlainConnectionSocketFactory sf = PlainConnectionSocketFactory
				.getSocketFactory();
		Socket socket = sf.createSocket(clientContext);
		int timeout = 1000; // ms
		HttpHost target = new HttpHost("localhost");
		InetSocketAddress remoteAddress = new InetSocketAddress(
				InetAddress.getByAddress(new byte[] { 127, 0, 0, 2 }), 80);
		sf.connectSocket(timeout, socket, target, remoteAddress, null,
				clientContext);
	}

	public static void main(String[] args) {
		try {
			test();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
