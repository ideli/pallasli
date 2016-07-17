package com.pallasli.study.casttest;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
//多播地址224-239段
// 注：若在互联网上运行需要支持多播的路由器的支持（不可能每个客户端都安装相应的路由器，因此多播可以说，只支持局域网。）

public class MultiCastSend {
	public static void main(String[] args) {
		try {
			/****************** 发送组播数据 ****************/
			// 指定组播IP
			InetAddress ip = InetAddress.getByName("228.5.6.7");
			InetAddress ip2 = InetAddress.getByName("228.5.6.72");
			// 也可以使用DatagramSocket但MulticastSocket更强大
			MulticastSocket s = new MulticastSocket(6789);
			// 加入该组
			s.joinGroup(ip);

			s.joinGroup(ip2);

			// 在多播中设置了TTl值（Time to live），每一个ip数据报文中都包含一个TTL，
			// 每当有路由器转发该报文时，TTL减1，知道减为0时，生命周期结束，报文即时没有到达目的地，
			// 也立即宣布死亡。当然在Java中，ttl并不是十分准确的，
			// 曾经在一本书中介绍过报文的传播距离是不会超过ttl所设置的值的。
			s.setTimeToLive(1);

			String msg = "IP:123123123;PORT:1231";
			DatagramPacket hi = new DatagramPacket(msg.getBytes(),
					msg.length(), ip, 6789);
			DatagramPacket hi2 = new DatagramPacket(msg.getBytes(),
					msg.length(), ip2, 6789);
			s.send(hi);
			s.send(hi2);
			/****************** 发送组播数据 ****************/
			s.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
