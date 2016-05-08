package com.pallasli.website.protocol;

import java.net.ServerSocket;

public class SocketServer {

	public final static int PORT = 9000;

	public SocketServer() {
	}

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(PORT);
			// serverSocket.setSoTimeout(5000);
			int i = 0;
			while (true) {
				new ServerThread(serverSocket.accept());
				System.out.println("i=" + ++i);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}
	}

}
