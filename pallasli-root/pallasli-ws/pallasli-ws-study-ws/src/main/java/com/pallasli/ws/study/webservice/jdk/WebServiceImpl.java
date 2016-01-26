package com.pallasli.ws.study.webservice.jdk;

import javax.jws.WebService;

import com.pallasli.ws.core.exception.PallasliWsException;
import com.pallasli.ws.study.ws.WebServiceI;

// 使用@WebService注解标注WebServiceI接口的实现类WebServiceImpl
@WebService(endpointInterface = "com.pallasli.ws.study.ws.WebServiceI")
// @HandlerChain(file = "handler-chain.xml")
public class WebServiceImpl implements WebServiceI {

	@Override
	public String sayHello(String name) throws PallasliWsException {
		if (name == null) {

			// 那么客户端和服务端都将抛出异常，因为服务端并未将runtimeException异常抛给客户端。
			// 抛出的异常客户端可以通过捕获SOAPFaultException来获取服务端的错误
			throw new RuntimeException();
		} else if (name.equals("exception")) {
			// 客户端抛出了SOAPFaultException异常，服务端没抛出异常
			throw new PallasliWsException();
		}
		System.out.println("WebService sayHello " + name);
		return "sayHello " + name;
	}

	@Override
	public String save(String name, String pwd) {
		System.out.println("WebService save " + name + "， " + pwd);
		return "save Success";
	}

}
