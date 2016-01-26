package com.pallasli.ws.study.webservice.jdk.handler;

import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.w3c.dom.Node;

public class ServiceSoapHandler implements SOAPHandler<SOAPMessageContext> {

	/**
	 * 先判断是否是发出去的消息,如果不是发出去的(即接收),如果客户端的saopheader为空或者密码123456则直接停止运行业务代码
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
				if (header != null) {
					Node node = header.getElementsByTagName("lic:userpassword")
							.item(0);
					String password = node.getTextContent();
					System.out.println("client send password:" + password);
					if (!"123456".equals(password))
						return false;
				} else {
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
