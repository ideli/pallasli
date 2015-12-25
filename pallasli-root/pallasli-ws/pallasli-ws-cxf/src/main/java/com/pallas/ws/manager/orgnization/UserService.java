package com.pallas.ws.manager.orgnization;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * service = new TestService(new URL("http://" + "localhost" + ":"
 * 
 * + "8081" + "/UserService?wsdl"), new QName(
 * 
 * "http://ws.ai.wasoft.com/", "UserService"));
 * 
 * Test test = service.getTestPort();
 * 
 * test.test2(1);
 * 
 * @author Administrator
 * 
 */
@WebService(serviceName = "UserService", targetNamespace = "http://ws.ai.wasoft.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class UserService {
	@WebMethod
	public String test2(int t) {
		try {
			System.out.println("http://ws.ai.wasoft.com/TestService");
		} catch (Exception e) {
		} finally {
		}
		return "";
	}

}
