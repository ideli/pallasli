package com.pallasli.ws.axis.sysutils;

import org.apache.axis.wsdl.WSDL2Java;

public class WSDL2JavaUtil {
	public static void main(String[] args) {
		/**
		 * -s 是设置wsdl的路径url和是当前物理硬盘上的wsdl文件（可以将页面上的wsdl的xml文件另存为一份试试，也是可以的）
		 * 
		 * -o 是设置转换的文件的输出目录
		 */
		WSDL2Java
				.main(new String[] {
						"-s http://localhost:8080/AxisWebService/services/HelloWorldWSDD?wsdl",
						"–o Users/lyt1987/Desktop/GitHub/pallasli/pallasli-root/pallasli-ws/pallasli-ws-axis/tmp" });

		/**
		 * 客户端通过生成的代码调用WebService
		 * 
		 * String target =
		 * "http://localhost:8080/AxisWebService/services/HelloWorldWSDD";
		 * 
		 * HelloWorldWSDDServiceLocator service = new
		 * HelloWorldWSDDServiceLocator();
		 * 
		 * HelloWorldWSDDSoapBindingStub stub = new
		 * HelloWorldWSDDSoapBindingStub(new URL(target), service);
		 * 
		 * System.out.println(stub.getAge(22));
		 * 
		 * System.out.println(stub.getName("张三"));
		 */
	}
}
