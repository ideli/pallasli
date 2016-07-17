package com.pallasli.study.casttest;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiCastReceive2 {

	public static void main(String[] args) {
		try {
			// IP组
			InetAddress ip = InetAddress.getByName("228.5.6.72");
			// 组播监听端口
			MulticastSocket s = new MulticastSocket(6789);
			// 加入该组
			s.joinGroup(ip);

			byte[] arb = new byte[24];
			while (true) {
				DatagramPacket datagramPacket = new DatagramPacket(arb,
						arb.length);
				s.receive(datagramPacket);
				System.out.println(new String(datagramPacket.getData(), 0,
						datagramPacket.getLength()));
				System.out.println(arb.length);
				System.out.println(new String(arb));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
