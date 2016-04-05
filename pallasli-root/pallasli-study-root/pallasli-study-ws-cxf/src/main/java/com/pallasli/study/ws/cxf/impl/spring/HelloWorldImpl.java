package com.pallasli.study.ws.cxf.impl.spring;

import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.pallasli.study.ws.cxf.HelloWorld;

@Component
@WebService
public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHi(String name) {
		return "hello " + name;
	}

}
