package com.pallasli.study.spring.rmi.server;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RmiServer {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/rmi/rmi_server_context.xml");
		System.out.println("RMI服务伴随Spring的启动而启动了.....");
	}
}
