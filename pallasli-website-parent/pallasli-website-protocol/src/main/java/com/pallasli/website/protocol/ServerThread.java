package com.pallasli.website.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerThread extends Thread {
	Socket socket = null;
	// BufferedReader in = null;
	// PrintWriter out = null;
	DataInputStream dis = null;
	DataOutputStream dos = null;

	public ServerThread(Socket s) throws IOException {
		socket = s;
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		// out = new PrintWriter(new BufferedWriter(new
		// OutputStreamWriter(socket.getOutputStream())),true);
		start();
	}

	@Override
	public void run() {
		int length = 0;
		try {

			while (true) {
				length = dis.available();

				if (length > 0) {
					byte[] bytes = new byte[length];
					dis.read(bytes, 0, length);
					System.out.println(new String(bytes));
				}

			} // end while
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			} // end try
		} // end try
	}

}
