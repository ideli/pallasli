package com.pallasli.study.ws.cxf.client;

import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.phase.Phase;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.pallasli.study.ws.cxf.HelloWorld;
import com.pallasli.study.ws.cxf.interceptor.ClientMessageInterceptor;

public class CXFClient {
	public static void main(String[] args) {
		JaxWsClient("http://localhost:8080/ws/hello");
		// DynamicClient("http://localhost:8080/ws/hello");
		// SpringClient();
		// SpringClientForSpring();
		// JaxWsClientInterceptor("http://localhost:8080/ws/hello");
		// SpringClientForSpringInterceptor();
	}

	/**
	 * 静态代理客户端
	 */
	public static void JaxWsClient(String address) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(address);
		factory.setServiceClass(HelloWorld.class);
		HelloWorld helloService = factory.create(HelloWorld.class);
		String result = helloService.sayHi("JaxWsClient");
		System.out.println(result);
	}

	public static void JaxWsClientInterceptor(String address) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(address);
		factory.setServiceClass(HelloWorld.class);
		factory.getInInterceptors().add(
				new ClientMessageInterceptor(Phase.RECEIVE));
		factory.getOutInterceptors().add(
				new ClientMessageInterceptor(Phase.SEND));
		HelloWorld helloService = factory.create(HelloWorld.class);
		String result = helloService.sayHi("JaxWsClient");
		System.out.println(result);
	}

	/**
	 * 通用动态代理客户端
	 */
	public static void DynamicClient(String address) {
		// CXF发布用的是业务类(HelloWorldImpl.java)，那么默认的命名空间就会是业务类所在包（路径），
		// 而对外界暴露的则是接口类(HelloWorld.java)，那么对于客户端（第三方）调用访问时，需要按照接口类所在包（路径）进行命名空间的定义
		QName opName = new QName("http://cxf.alibaba.com/", "sayHi"); // 指定到接口类所在包
		address = address + "?wsdl";
		DynamicClientFactory factory = DynamicClientFactory.newInstance();
		Client client = factory.createClient(address);
		try {
			Object[] results = client.invoke(opName, "DynamicClient");
			System.out.println(results[0]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 如果接口和实现在同一路径下，就使用下面的代码.
		// address = address+"?wsdl";
		// DynamicClientFactory factory = DynamicClientFactory.newInstance();
		// Client client = factory.createClient(address);
		// try {
		// Object[] results = client.invoke("sayHi", "DynamicClient");
		// System.out.println(results[0]);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * spring客户端
	 */
	public static void SpringClient() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-client.xml");
		HelloWorld helloService = context.getBean("client", HelloWorld.class);
		String result = helloService.sayHi("SpringClient");
		System.out.println(result);
	}

	/**
	 * spring客户端
	 */
	public static void SpringClientForSpring() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-client-for-spring.xml");
		HelloWorld helloService = context.getBean("client", HelloWorld.class);
		String result = helloService.sayHi("SpringClient");
		System.out.println(result);
	}

	public static void SpringClientForSpringInterceptor() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"spring-client-for-spring-interceptor.xml");
		HelloWorld helloService = context.getBean("client", HelloWorld.class);
		String result = helloService.sayHi("SpringClient");
		System.out.println(result);
	}
}
