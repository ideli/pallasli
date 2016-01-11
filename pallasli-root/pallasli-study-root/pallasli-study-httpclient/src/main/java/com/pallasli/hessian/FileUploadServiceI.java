package com.pallasli.hessian;

import java.io.InputStream;

/**
 * <p>
 * ClassName: FileUploadServiceI
 * <p>
 * <p>
 * Description: 文件上传服务接口
 * <p>
 * 
 * @author xudp
 * @version 1.0 V
 * @createTime 2014-8-8 上午10:41:27
 */
public interface FileUploadServiceI {

	public void upload(String filename, InputStream data);
}