package com.pallasli.study.casttest;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class BroadCastReceive {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			DatagramSocket ds = new DatagramSocket(3001);// 创建接收数据报套接字并将其绑定到本地主机上的指定端口
			byte[] buf = new byte[1024];
			DatagramPacket dp = new DatagramPacket(buf, buf.length);
			ds.receive(dp);
			// String strRecv = new String(dp.getData(), 0, dp.getLength()) +
			// " from " + dp.getAddress().getHostAddress() + ":" + dp.getPort();
			System.out.println(new String(buf));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
