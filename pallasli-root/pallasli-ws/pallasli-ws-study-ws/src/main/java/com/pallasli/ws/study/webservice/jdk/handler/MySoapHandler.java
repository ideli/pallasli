package com.pallasli.ws.study.webservice.jdk.handler;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class MySoapHandler implements SOAPHandler<SOAPMessageContext> {
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		System.out.println("run handleFault method!");
		return false;
	}

	/**
	 * 先判断是否是发出去的消息，然后再在soapheader中添加消息userpassword
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		System.out.println("run handleMessage method!");
		Boolean out = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		System.out.println(out);
		if (out) {
			SOAPMessage message = context.getMessage();
			try {
				SOAPEnvelope senv = message.getSOAPPart().getEnvelope();
				SOAPHeader header = senv.getHeader();
				if (header == null) {
					header = senv.addHeader();
				}
				QName qname = new QName("http://ws01.com/", "userpassword",
						"lic");
				header.addChildElement(qname).setValue("123456");
				message.writeTo(System.out);
				System.out.println();
			} catch (SOAPException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 返回true表示继续执行之后的业务代码，否则停止运行业务代码
		return true;
	}

	@Override
	public void close(MessageContext context) {
		System.out.println("run close method!");
	}

	@Override
	public Set<QName> getHeaders() {
		return null;
	}
}