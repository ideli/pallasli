package com.pallasli.hessian;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import com.caucho.hessian.client.HessianProxyFactory;

/**
 * <p>
 * ClassName: FileUploaderClient
 * <p>
 * <p>
 * Description: 文件上传客户端
 * <p>
 * 
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-8-8 上午10:00:13
 */
public class FileUploaderClient {
	// Hessian服务的url
	private static final String url = "http://gacl:8080/FileUploader/FileUploadService";

	public static void main(String[] args) throws Exception {
		// 创建HessianProxyFactory实例
		HessianProxyFactory factory = new HessianProxyFactory();
		// 获得Hessian服务的远程引用
		FileUploadServiceI uploader = (FileUploadServiceI) factory.create(
				FileUploadServiceI.class, url);
		// 读取需要上传的文件
		InputStream data = new BufferedInputStream(new FileInputStream(
				"E:/开发资料/电子书/Shiro教程.pdf"));
		// 调用远程服务上传文件。
		uploader.upload("Shiro教程.pdf", data);
	}
}
