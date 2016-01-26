package com.pallasli.ws.study.webservice.jdk;

import javax.jws.WebService;

import com.pallasli.ws.study.ws.WebServiceI;

@WebService
public class WebServiceImpl2 implements WebServiceI {

	@Override
	public String sayHello(String name) {
		System.out.println("WebService sayHello " + name);
		return "sayHello " + name;
	}

	@Override
	public String save(String name, String pwd) {
		System.out.println("WebService save " + name + "， " + pwd);
		return "save Success";
	}

}
