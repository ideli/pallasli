package com.pallasli.study.ws.cxf;

import javax.jws.WebService;

@WebService
public interface HelloWorld {
	String sayHi(String name);
}
