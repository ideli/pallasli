package com.pallasli.study.spring.rmi.server;

import java.io.Serializable;

import com.pallasli.study.spring.rmi.RMIService;

public class RMIServiceImpl implements RMIService,
		Serializable {

	public boolean setFeedback(String systemID) {
		System.out.println(systemID);
		return false;
	}
}