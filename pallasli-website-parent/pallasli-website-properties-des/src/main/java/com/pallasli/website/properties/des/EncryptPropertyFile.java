package com.pallasli.website.properties.des;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.pallasli.utils.PropertiesUtils;
import com.pallasli.website.encrypt.des.EncryptArithmetic;

public class EncryptPropertyFile {

	static EncryptArithmetic decrypt = new EncryptArithmetic();

	public static void main(String[] args) {
		String filePath = "";
		Properties prop = PropertiesUtils.loadProperties(filePath);
		OutputStream fos;
		try {
			fos = new FileOutputStream(filePath);
			prop.setProperty("username", decrypt.e(prop.getProperty("username")));
			prop.setProperty("password", decrypt.e(prop.getProperty("password")));

			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			prop.store(fos, "Update 'username' value");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
