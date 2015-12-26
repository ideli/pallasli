package com.pallasli.dubbo.sys;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pallasli.dubbo.service.DemoService;

public class TestClient {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring.client.xml" });
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("world"); // 执行远程方法
		System.out.println(demoService);
		System.out.println(hello);
	}
}