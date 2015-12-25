package com.lyt.client.service;

import javax.xml.namespace.QName;
import javax.xml.rpc.Call;

import org.apache.axis.client.Service;

public class StatuteClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "http://localhost:10010/pallasli";
		Service se = new Service();
		try {
			Call call = se.createCall();
			call.setTargetEndpointAddress(url);
			call.setOperationName(new QName(url, "findStatuteForXml"));
			String result = (String) call.invoke(new Object[] { "", "" });
			call.setOperationName(new QName(url, "findStatuteForJson"));
			String result2 = (String) call.invoke(new Object[] { "", "" });
			System.out.println(result);
			System.out.println(result2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
