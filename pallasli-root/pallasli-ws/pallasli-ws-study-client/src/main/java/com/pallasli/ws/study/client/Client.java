package com.pallasli.ws.study.client;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;

import org.junit.Test;
import org.w3c.dom.Document;

import com.pallasli.ws.core.exception.PallasliWsException;
import com.pallasli.ws.study.ws.WebServiceI;

public class Client {
	public static void main(String[] args) {
		test4();
	}

	public static void test4() {
		try {
			String namespace = "http://jdk.webservice.study.ws.pallasli.com/";
			String wsdlUrl = "http://localhost:8989/WS_Server/Webservice?wsdl";

			// 1、创建服务(Service)
			URL url = new URL(wsdlUrl);
			QName qname = new QName(namespace, "WebServiceImplService");
			Service service = Service.create(url, qname);

			// 2、创建Dispatch
			// public interface Dispatch<T>extends BindingProviderDispatch
			// 接口提供对动态调用服务端点操作的支持。javax.xml.ws.Service 接口作为创建 Dispatch 实例的工厂。
			Dispatch<SOAPMessage> dispatch = service.createDispatch(new QName(
					namespace, "WebServiceImplPort"), SOAPMessage.class,
					Service.Mode.MESSAGE);

			// 3.1、创建SOAPMessage
			SOAPMessage message = MessageFactory.newInstance().createMessage();
			SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
			SOAPBody body = envelope.getBody();

			// 3.2、处理header信息
			SOAPHeader header = envelope.getHeader();
			if (header == null)
				header = envelope.addHeader();
			QName hname = new QName(namespace, "arg0", "nn");
			header.addHeaderElement(hname).setValue("administrator");

			// 4、创建QName来指定消息中传递数据
			QName ename = new QName(namespace, "sayHello", "nn");
			body.addBodyElement(ename);
			message.writeTo(System.out);
			System.out.println("\n*");
			System.out.println(message);

			// 5、通过Dispatch传递消息,并返回响应消息
			SOAPMessage returnMessage = dispatch.invoke(message);
			returnMessage.writeTo(System.out);// 打印返回消息
			System.out.println("\n*");
			System.out.println(returnMessage);

			// 5、解析返回的SOAP消息的XML
			Document doc = returnMessage.getSOAPBody()
					.extractContentAsDocument();
			System.out.println("\n*");
			System.out.println(doc);
			// Document doc =
			// returnMessage.getSOAPPart().getEnvelope().getBody().extractContentAsDocument();
			String result = doc.getElementsByTagName("return").item(0)
					.getTextContent();
			System.out.println("*");
			System.out.println("result is :" + result);

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testqm() {

		// 需要使用接口作为endpointInterface,否则WebServiceI client =
		// service.getPort(WebServiceI.class);无法根据接口获得WebService
		String namespaceUrl = "http://jdk.webservice.study.ws.pallasli.com/";

		URL url = null;
		try {
			url = new URL("http://localhost:8989/WS_Server/Webservice?wsdl");// 使用namespaceURL和服务名创建QName
			// <definitions ... targetNamespace="http://jdk.ws.pallasli.com/"
			// name="WebServiceImplService">
			// <service name="WebServiceImplService">
			QName qname = new QName(namespaceUrl, "WebServiceImplService");

			// 创建服务
			Service service = Service.create(url, qname);
			// 服务器和客户端在同一机器时可以直接用服务的接口类,不在同一机器的具体用法见后面的章节
			WebServiceI client = service.getPort(WebServiceI.class);

			// 调用WebService的sayHello方法
			String resResult;
			try {
				resResult = client.sayHello("孤傲苍狼");
				System.out
						.println("调用WebService的sayHello方法返回的结果是：" + resResult);
			} catch (PallasliWsException e) {
				e.printStackTrace();
			}
			System.out
					.println("---------------------------------------------------");
			// 调用WebService的save方法
			resResult = client.save("孤傲苍狼", "123");
			System.out.println("调用WebService的save方法返回的结果是：" + resResult);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// @Test
	public void testqm2() {

		// 需要使用接口作为endpointInterface
		String namespaceUrl = "http://jdk.webservice.study.ws.pallasli.com/";

		URL url = null;
		try {
			url = new URL("http://localhost:8989/WS_Server/Webservice2?wsdl");// 使用namespaceURL和服务名创建QName
			// <definitions ... targetNamespace="http://jdk.ws.pallasli.com/"
			// name="WebServiceImplService">
			// <service name="WebServiceImplService">
			QName qname = new QName(namespaceUrl, "CopyOfWebServiceImplService");

			// 创建服务
			Service service = Service.create(url, qname);

			// 服务器和客户端在同一机器时可以直接用服务的接口类,不在同一机器的具体用法见后面的章节
			WebServiceI client = service.getPort(WebServiceI.class);

			// 调用WebService的sayHello方法
			String resResult = client.sayHello("孤傲苍狼");
			System.out.println("调用WebService的sayHello方法返回的结果是：" + resResult);
			System.out
					.println("---------------------------------------------------");
			// 调用WebService的save方法
			resResult = client.save("孤傲苍狼", "123");
			System.out.println("调用WebService的save方法返回的结果是：" + resResult);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PallasliWsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
