package com.pallasli.ws.study.client;

import org.junit.Test;

import com.pallasli.client.test.ws.wsimport.WebServiceImpl2;
import com.pallasli.client.test.ws.wsimport.WebServiceImpl2Service;
import com.pallasli.client.test.ws.wsimport.WebServiceImplService;

public class RunWithImportJar {
	@Test
	public void testImport() {
		// 使用接口作为endpointInterface和使用默认的实现类作为endpointInterface使用的生成类有大的变化
		// 创建一个用于产生WebServiceImpl实例的工厂，WebServiceImplService类是wsimport工具生成的
		WebServiceImplService factory = new WebServiceImplService();
		// 通过工厂生成一个WebServiceImpl实例，WebServiceImpl是wsimport工具生成的
		com.pallasli.client.test.ws.wsimport.WebServiceI wsImpl = factory
				.getWebServiceImplPort();
		// 调用WebService的sayHello方法
		String resResult;
		try {
			resResult = wsImpl.sayHello(null);
			System.out.println("调用WebService的sayHello方法返回的结果是：" + resResult);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out
				.println("---------------------------------------------------");
		// 调用WebService的save方法
		resResult = wsImpl.save("孤傲苍狼", "123");
		System.out.println("调用WebService的save方法返回的结果是：" + resResult);

	}

	@Test
	public void testImport2() {
		// 使用接口作为endpointInterface和使用默认的实现类作为endpointInterface使用的生成类有大的变化
		// 创建一个用于产生WebServiceImpl实例的工厂，WebServiceImplService类是wsimport工具生成的
		WebServiceImpl2Service factory = new WebServiceImpl2Service();
		// 通过工厂生成一个WebServiceImpl实例，WebServiceImpl是wsimport工具生成的
		WebServiceImpl2 wsImpl = factory.getWebServiceImpl2Port();
		// 调用WebService的sayHello方法
		String resResult = wsImpl.sayHello("孤傲苍狼");
		System.out.println("调用WebService的sayHello方法返回的结果是：" + resResult);
		System.out
				.println("---------------------------------------------------");
		// 调用WebService的save方法
		resResult = wsImpl.save("孤傲苍狼", "123");
		System.out.println("调用WebService的save方法返回的结果是：" + resResult);

	}

}
