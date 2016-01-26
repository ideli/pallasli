package com.lyt.client.handler;

import java.rmi.RemoteException;
import java.util.Date;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.soap.SOAPException;

import org.apache.axis.Message;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.message.MessageElement;
import org.apache.axis.message.SOAPEnvelope;
import org.apache.axis.message.SOAPHeaderElement;

/**
 * <b>function:</b>Header操作WebService客户端
 * 
 * @author hoojo
 * @createDate Dec 19, 2010 2:18:51 PM
 * @file OperaterHeaderClient.java
 * @package com.hoo.client
 * @project AxisWebService
 * @blog http://blog.csdn.net/IBM_hoojo
 * @email hoojo_@126.com
 * @version 1.0
 */
public class OperaterHeaderClient {

	public static void main(String[] args) throws SOAPException,
			ServiceException, RemoteException {
		String url = "http://localhost:8080/AxisWebService/services/OperaterHeader";
		// 创建服务
		Service service = new Service();
		// 创建调用句柄
		Call call = (Call) service.createCall();
		// 设置请求地址
		call.setTargetEndpointAddress(url);
		call.setOperationName(new QName("readHeader"));

		/** 添加请求的header */
		// 父节点，根节点
		SOAPHeaderElement el = new SOAPHeaderElement(
				new QName("soap", "Header"));
		// 文本节点
		MessageElement msgEl = new MessageElement(new QName("ServiceName"),
				"ReadHeaderService");
		el.addChild(msgEl);

		// 文本节点
		el.addChild(new MessageElement(new QName("CreateDate"), new Date()));
		el.addChild(new MessageElement(new QName("service", "Version"), "1.0"));
		// 子元素
		msgEl = new MessageElement(new QName("author"));
		msgEl.addChild(new MessageElement(new QName("name"), "axis"));
		msgEl.addChild(new MessageElement(new QName("age"), "14"));
		el.addChild(msgEl);
		// 子元素
		msgEl = new MessageElement(new QName("copyRight"));
		msgEl.addChild(new MessageElement(new QName("year"), "2010"));
		msgEl.addChild(new MessageElement(new QName("number"), "123123123"));
		el.addChild(msgEl);
		el.addChild(new MessageElement(new QName("Tel"), "555666442"));

		System.out.println(el.toString());
		// 设置请求的header
		call.addHeader(el);

		/**
		 * 注意：在请求readHeader方法的时候，也就是invoke执行的时候。会调用ReadHeaderHandler这个handler
		 * 在请求完成后，准备返回值的时候或是说函数在运行完成了后。就会触发WriteHeaderHandler
		 */
		call.invoke(new Object[] {});

		/** 响应的header */
		Message msg = call.getResponseMessage();
		SOAPEnvelope se = msg.getSOAPEnvelope();
		System.out.println("responseMessage:" + se.getHeader());
	}
}
