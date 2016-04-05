package com.pallasli.study.ws.cxf.impl;

import javax.jws.WebService;

import com.pallasli.study.ws.cxf.HelloWorld;

@WebService
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHi(String name) {
		System.out.println("sayHi is called");
		return "hello " + name;
	}

}
