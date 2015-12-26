package com.pallasli.javastudy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Properties;

import org.junit.Test;

public class InterUtils {
	@Test
	public void getIp() {
		InetAddress address = null;
		try {
			address = InetAddress.getByName("www.yiibai.com");
		} catch (UnknownHostException e) {
			System.exit(2);
		}
		System.out.println(address.getHostName() + "="
				+ address.getHostAddress());
		System.exit(0);
	}

	@Test
	public void connectSocket() {
		try {
			InetAddress addr;
			Socket sock = new Socket("www.yiibai.com", 80);
			addr = sock.getInetAddress();
			System.out.println("Connected to " + addr);
			sock.close();
		} catch (java.io.IOException e) {
			System.out.println("Can't connect to ");
			System.out.println(e);
		}
	}

	@Test
	public void checkModified() throws Exception {
		URL u = new URL("http://127.0.0.1/java.bmp");
		URLConnection uc = u.openConnection();
		uc.setUseCaches(false);
		long timestamp = uc.getLastModified();
		System.out.println("The last modification time of java.bmp is :"
				+ timestamp);
	}

	@Test
	public void getSize() throws Exception {
		int size;
		URL url = new URL("http://www.server.com");
		URLConnection conn = url.openConnection();
		size = conn.getContentLength();
		if (size < 0)
			System.out.println("Could not determine file size.");
		else
			System.out.println("The size of file is = " + size + "bytes");
		conn.getInputStream().close();
	}

	@Test
	public void getServerSocket() throws Exception {
		ServerSocket ssock = new ServerSocket(1234);
		System.out.println("Listening");
		Socket sock = ssock.accept();
		InetAddress addr = sock.getInetAddress();
		System.out.println("Connection made to " + addr.getHostName() + " ("
				+ addr.getHostAddress() + ")");
		Thread.sleep(5000);
		ssock.close();

		PrintStream ps = new PrintStream(sock.getOutputStream());
		for (int i = 10; i >= 0; i--) {
			ps.println(i + " from Java Source and Support.");
		}
		ps.close();
		sock.close();
	}

	@Test
	public void getUrlInfo() throws Exception {
		URL url = new URL("http://www.server.com");
		System.out.println("URL is " + url.toString());
		System.out.println("protocol is " + url.getProtocol());
		System.out.println("file name is " + url.getFile());
		System.out.println("host is " + url.getHost());
		System.out.println("path is " + url.getPath());
		System.out.println("port is " + url.getPort());
		System.out.println("default port is " + url.getDefaultPort());
		HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
		long date = httpCon.getDate();
		if (date == 0)
			System.out.println("No date information.");
		else
			System.out.println("Date: " + new Date(date));
	}

	@Test
	public void readDownload() throws Exception {
		URL url = new URL("http://www.yiibai.com");
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				url.openStream()));
		BufferedWriter writer = new BufferedWriter(new FileWriter("data.htmll"));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
			writer.write(line);
			writer.newLine();
		}
		reader.close();
		writer.close();
	}

	@Test
	public void getHost() throws Exception {
		InetAddress addr = InetAddress.getByName("74.125.67.100");
		System.out.println("Host name is: " + addr.getHostName());
		System.out.println("Ip address is: " + addr.getHostAddress());
	}

	@Test
	public void getLocalHost() throws Exception {
		InetAddress addr = InetAddress.getLocalHost();
		System.out
				.println("Local HostAddress: 	      " + addr.getHostAddress());
		String hostname = addr.getHostName();
		System.out.println("Local host name: " + hostname);
	}

	@Test
	public void checkPortUsed() throws Exception {
		Socket skt = null;
		String host = "localhost";
		for (int i = 0; i < 1024; i++) {
			try {
				System.out.println("Looking for " + i);
				skt = new Socket(host, i);
				System.out.println("There is a server on port " + i + " of "
						+ host);
				skt.close();
			} catch (UnknownHostException e) {
				System.out.println("Exception occured" + e);
				break;
			} catch (IOException e) {
			}
		}
	}

	@Test
	public void findProxy() throws Exception {
		try {
			Properties systemSettings = System.getProperties();
			systemSettings.put("proxySet", "true");
			systemSettings.put("http.proxyHost", "proxy.mycompany1.local");
			systemSettings.put("http.proxyPort", "80");
			URL u = new URL("http://www.google.com");
			HttpURLConnection con = (HttpURLConnection) u.openConnection();
			System.out.println(con.getResponseCode() + " : "
					+ con.getResponseMessage());
			System.out
					.println(con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(false);
		}
		System.setProperty("java.net.useSystemProxies", "true");
		Proxy proxy = ProxySelector.getDefault()
				.select(new URI("http://www.yahoo.com/")).iterator().next();
		;
		System.out.println("proxy hostname : " + proxy.type());
		InetSocketAddress addr = (InetSocketAddress) proxy.address();
		if (addr == null) {
			System.out.println("No Proxy");
		} else {
			System.out.println("proxy hostname : " + addr.getHostName());
			System.out.println("proxy port : " + addr.getPort());
		}
	}

	@Test
	public void createSocketWithPort() throws Exception {
		try {
			InetAddress addr = InetAddress.getByName("121.125.67.85");
			Socket theSocket = new Socket(addr, 80);
			System.out.println("Connected to " + theSocket.getInetAddress()
					+ " on port " + theSocket.getPort() + " from port "
					+ theSocket.getLocalPort() + " of "
					+ theSocket.getLocalAddress());
			theSocket.close();
		} catch (UnknownHostException e) {
			System.err.println("I can't find " + e);
		} catch (SocketException e) {
			System.err.println("Could not connect to " + e);
		} catch (IOException e) {
			System.err.println(e);
		}
	}
}
