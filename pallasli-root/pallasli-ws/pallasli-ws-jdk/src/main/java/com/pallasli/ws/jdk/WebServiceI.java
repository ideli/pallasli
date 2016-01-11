package com.pallasli.ws.jdk;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface WebServiceI {
	// 使用@WebMethod注解标注WebServiceI接口中的方法
	@WebMethod
	String sayHello(String name) throws MyException;

	/**
	 * @WebResult(name="addResult") 此注解可加可不加，如果加了但不指定name属性的值跟没加是一样的，
	 *                              加上name的效果就是在wsdl文件的定义中将该方法的返回值的名称固定了，
	 *                              而不是【方法名Response
	 *                              】,例如add方法的返回参数的定义将为：addResponse
	 * 
	 * @WebParam(name="a") 此注解是将方法的参数的名称用一个有意义的名称进行定义,
	 *                     如果不定义那wsdl中将是arg0、arg1....这种无意义的名称
	 */
	@WebMethod
	String save(String name, String pwd);
}
