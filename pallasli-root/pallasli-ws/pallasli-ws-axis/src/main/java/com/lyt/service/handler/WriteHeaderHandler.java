package com.lyt.service.handler;

import java.util.Iterator;

import javax.xml.soap.SOAPException;

import org.apache.axis.AxisFault;
import org.apache.axis.Message;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeader;
import org.apache.axis.message.SOAPHeaderElement;

/**
 * <b>function:</b>用handler向客户端写入Response 的Header信息
 * 
 * @author hoojo
 * @createDate Dec 19, 2010 2:08:35 PM
 * @file WriteHeaderHandler.java
 * @package com.hoo.service.handler
 * @project AxisWebService
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class WriteHeaderHandler extends BasicHandler {

	private static final long serialVersionUID = 1L;

	@Override
	@SuppressWarnings("unchecked")
	public void invoke(MessageContext msgContext) throws AxisFault {
		Message msg = msgContext.getCurrentMessage();
		SOAPEnvelope se = msg.getSOAPEnvelope();

		// request Header的部分头信息
		Message reqMsg = msgContext.getRequestMessage();
		SOAPEnvelope reqSe = reqMsg.getSOAPEnvelope();
		SOAPHeader reqHeader = null;
		try {
			reqHeader = (SOAPHeader) reqSe.getHeader();
		} catch (SOAPException e) {
			e.printStackTrace();
		}

		// response Header部分头信息
		Message respMsg = msgContext.getResponseMessage();
		SOAPEnvelope respSe = respMsg.getSOAPEnvelope();
		SOAPHeader respHeader = null;
		try {
			respHeader = (SOAPHeader) respSe.getHeader();
		} catch (SOAPException e) {
			e.printStackTrace();
		}

		Iterator<SOAPHeaderElement> iter = reqHeader.examineAllHeaderElements();
		while (iter.hasNext()) {
			SOAPHeaderElement she = iter.next();
			try {
				// 添加请求信息到响应的Header中
				respHeader.addChildElement(she);
			} catch (SOAPException e) {
				e.printStackTrace();
			}
			System.out.println(she.getNodeName());
		}
		// 设置响应信息
		msgContext.setResponseMessage(respMsg);
		try {
			System.out.println("header Info:" + se.getHeader());
		} catch (SOAPException e) {
			e.printStackTrace();
		}
	}
}