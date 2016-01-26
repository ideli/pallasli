package com.pallasli.ws.study.webservice.jdk;

import javax.xml.ws.Endpoint;

public class WebServicePublish {

	/**
	 * web项目在servlet初始化或servletContextListener中发布
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 定义WebService的发布地址，这个地址就是提供给外界访问Webervice的URL地址，URL地址格式为：http://ip:端口号/xxxx
		String address = "http://localhost:8989/WS_Server/Webservice";
		String address2 = "http://localhost:8989/WS_Server/Webservice2";
		// 使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
		Endpoint.publish(address, new WebServiceImpl());
		Endpoint.publish(address2, new WebServiceImpl2());
		System.out.println("发布webservice成功!");
	}
}
