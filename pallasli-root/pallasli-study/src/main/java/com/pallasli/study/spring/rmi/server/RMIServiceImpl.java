package com.pallasli.study.spring.rmi.server;

import java.io.Serializable;

import com.pallasli.study.spring.rmi.RMIService;

public class RMIServiceImpl implements RMIService, Serializable {

	@Override
	public boolean setFeedback(String systemID) {
		System.out.println("*");
		System.out.println("*");
		System.out.println("*");
		System.out.println(systemID);
		System.out.println("*");
		return false;
	}
}