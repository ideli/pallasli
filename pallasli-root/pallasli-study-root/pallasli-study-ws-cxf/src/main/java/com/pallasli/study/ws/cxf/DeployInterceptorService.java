package com.pallasli.study.ws.cxf;

import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.phase.Phase;

import com.pallasli.study.ws.cxf.impl.HelloWorldImpl;
import com.pallasli.study.ws.cxf.interceptor.MessageInterceptor;

public class DeployInterceptorService {

	// 这里设置的是服务端的拦截器
	// 也可以配置客户端的拦截器，客户端先出后入，服务端先入后出
	public static void main(String[] args) throws InterruptedException {
		// 发布WebService
		JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean();
		// 设置Service Class
		factory.setServiceClass(HelloWorld.class);
		factory.setAddress("http://localhost:8080/ws/hello");
		// 设置ServiceBean对象
		factory.setServiceBean(new HelloWorldImpl());

		// 添加请求和响应的拦截器，Phase.RECEIVE只对In有效，Phase.SEND只对Out有效
		// 相当于before和after监听
		factory.getInInterceptors().add(new MessageInterceptor(Phase.RECEIVE));
		factory.getOutInterceptors().add(new MessageInterceptor(Phase.SEND));

		factory.create();

		System.out.println("Server start ......");
		Thread.sleep(1000 * 60);
		System.exit(0);
		System.out.println("Server exit ");
	}

}
