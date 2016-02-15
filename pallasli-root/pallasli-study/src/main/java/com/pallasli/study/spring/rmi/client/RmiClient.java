package com.pallasli.study.spring.rmi.client;

import java.rmi.RemoteException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pallasli.study.spring.rmi.RMIService;

public class RmiClient {
	public static void main(String[] args) throws RemoteException {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/rmi/rmi_client_context.xml");
		RMIService hs = (RMIService) ctx.getBean("helloWorld");
		System.out.println(hs.setFeedback("tea"));
	}
}
