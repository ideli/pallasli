package com.pallasli.dubbo.sys;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StartService {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring.service.xml" });
		context.start();

		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
	}
}
