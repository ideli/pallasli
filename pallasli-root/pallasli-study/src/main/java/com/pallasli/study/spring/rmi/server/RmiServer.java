package com.pallasli.study.spring.rmi.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiServer {
	public static void main(String[] args) {
		// Rmi在Spring中的使用之RmiServiceExporter
		// 当我们在启动服务端的时候会发现，其控制台一直在运行状态，当结束后，还会有rmi进程在运行。其接口协议为rmi://hostname:1199/xxxxxxx
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/rmi/rmi_server_context.xml");
		System.out.println("RMI服务伴随Spring的启动而启动了.....");
	}
}
