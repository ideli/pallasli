package com.pallasli.website.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress address = null;
		Socket socket = null;
		// BufferedReader in = null;
		// PrintWriter out = null;
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try {
			address = InetAddress.getLocalHost();
			socket = new Socket(address, 20248);
			// socket = new Socket(address, 9000);
			// in = new BufferedReader(new
			// InputStreamReader(socket.getInputStream()));
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			for (;;) {
				dos.write("123456789".getBytes(), 0, "123456789".getBytes().length - 1);
				// String consoleIn = console.readLine();
				// out.write(consoleIn);
				// out.flush();
				// if (consoleIn != null && consoleIn.equals("end")) {
				// break ;
				// }
				// String read = in.readLine();
				// System.out.println("From server=" + read);
			} // end for
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				dis.close();
				dos.close();
				socket.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
