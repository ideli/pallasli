package com.pallasli.study.jnative;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.misc.basicStructures.LONG;

/**
 * 1.下载jnative。jar 及JNativeCpp.dll
 * 
 * 2.将使用的dll文件及JNativeCpp.dll拷贝至系统system32下
 * 
 * 3.样码
 * 
 * 1.JNativeCpp.dll 文件放入到system32目录和本地工程目录下；
 * 
 * 2.test.dll(自己编写的dll)放入到system32目录和本地工程目录下；（经测试：JNative调用动态链接库，
 * 需要把该dll放置在system32下，本地工程目录下无需放置，系统会自动到System32目录下寻找。）
 * 
 * 3.加入test.dll文件中应用到的一些*.dat文件，需要将这些文件放置到jdk的bin目录下；
 * 
 * 4.加入test.dll文件中应用到的一些*.lib 文件，需要将这些文件放置到jdk的根目录下；
 * 
 * 5.JNative.jar放置在工程的目录下；
 * 
 * Web工程：（以Tomcat为例）
 * 
 * 1.JNativeCpp.dll 文件放入到system32目录，本地工程目录下和Tomcat的bin目录下；
 * 
 * 2.JNative.jar放置在工程的目录和Tomcat的lib目录下；
 * 
 * 3.加入test.dll文件中应用到了一些*.dat文件，需要将这些文件放置到jdk的bin目录下；
 * 
 * 4.加入test.dll文件中应用到得一些*.lib 文件，需要将这些文件放置到jdk的根目录下；
 * 
 * 5.test.dll(自己编写的dll)放入到system32目录和本地工程目录下；（经测试：JNative调用动态链接库，
 * 需要把该dll放置在system32下，本地工程目录下无需放置，系统会自动到System32目录下寻找。）
 * 
 * @author lyt
 * 
 */
public class JNativeT {

	static JNative PegRoute = null;

	public String init() throws NativeException, IllegalAccessException {
		try {
			if (PegRoute == null) {
				// 1. 利用org.xvolks.jnative.JNative来加载DLL：参数1.PegRoute为类名
				// 2.HCTInitEx方法名
				PegRoute = new JNative("PegRoute", "HCTInitEx");

				// 2.设置要调用方法中的参数：0 表示第一个以此类推
				LONG versionLong = new LONG(10);
				versionLong.setValue(0);

				PegRoute.setParameter(0, Type.LONG, versionLong.toString());
				PegRoute.setParameter(1, Type.STRING, "");

				// 3.设置返回参数的类型
				PegRoute.setRetVal(Type.INT);
				// 4.执行方法
				PegRoute.invoke();// 调用方法
			}
			System.out.println("调用的DLL文件名为：" + PegRoute.getDLLName());
			System.out.println("调用的方法名为：" + PegRoute.getFunctionName());
			// 5.返回值
			return PegRoute.getRetVal();
		} finally {
			if (PegRoute != null) {
				// 6.释放系统资源
				PegRoute.dispose();
			}
		}
	}

	public static void main(String[] args) throws NativeException,
			IllegalAccessException {

		String mm = new JNativeT().init();
		System.out.println(mm);
	}
}
