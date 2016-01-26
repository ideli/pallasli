package com.pallasli.ws.study.webservice.jdk.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.Node;

public class AuthCheckHandler implements SOAPHandler<SOAPMessageContext> {

	/**
	 * 校验用户名密码、校验是否有权限执行方法
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		Boolean out = (Boolean) context
				.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY);
		if (!out) {
			SOAPMessage message = context.getMessage();
			try {
				SOAPHeader header = message.getSOAPPart().getEnvelope()
						.getHeader();
				SOAPBody body = message.getSOAPPart().getEnvelope().getBody();

				// 如果头信息为空或者没有内容,直接抛出异常
				if (null == header || header.getChildNodes().getLength() == 0) {
					SOAPFault fault = body.addFault();
					fault.setFaultString("头信息不能为空！");
					throw new SOAPFaultException(fault);
				}
				// 校验用户名和密码
				Node node = header.getElementsByTagName("lic:userpassword")
						.item(0);
				String password = node.getTextContent();
				System.out.println("client send password:" + password);
				if (!"123456".equals(password)) {
					return false;
				}

				// 校验是否有执行方法的权限
				Node bd = body.getFirstChild();
				String methodName = bd.getLocalName();// 调用的方法名
				System.out.println(methodName);
				// 这个方法不允许该用户调用
				if ("adminLogin".equals(methodName)) {
					return false;
				}
			} catch (SOAPException e) {
				e.printStackTrace();
			}

		}
		return true;
	}

	@Override
	public Set<QName> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}
}