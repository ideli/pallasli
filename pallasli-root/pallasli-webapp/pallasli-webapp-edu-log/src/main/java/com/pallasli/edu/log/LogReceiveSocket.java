package com.pallasli.edu.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class LogReceiveSocket {
	int port = 14567;

	private BufferedReader getReader(Socket socket) throws IOException {
		InputStream socketIn = socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn, "UTF-8"));
	}

	void start() {
		Socket s = null;
		try {

			// SocketServer server=new SocketServer(new File(""));
			// server.
			ServerSocket ss = new ServerSocket(port);
			while (true) {
				s = ss.accept();
				System.out.println("建立socket链接");
				BufferedReader br = getReader(s);
				String line = null;
				while ((line = br.readLine()) != null) {
					System.out.println("getline:" + line);
				}
				System.out.println("lastline:" + line);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String arg[]) {
		new LogReceiveSocket().start();
	}
}
