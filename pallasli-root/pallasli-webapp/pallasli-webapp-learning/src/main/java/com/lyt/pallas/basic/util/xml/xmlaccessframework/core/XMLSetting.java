/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lyt.pallas.basic.util.xml.xmlaccessframework.core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Administrator
 */
public class XMLSetting {

	private String settingfilepath;
	private static XMLSetting dbsetting;

	public static XMLSetting getXMLSetting() {
		if (dbsetting == null) {
			dbsetting = new XMLSetting();
		} else {
			return dbsetting;
		}
		return dbsetting;
	}

	public String readXMLPath() {
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(this.getSettingFilePath()));
		} catch (FileNotFoundException e) {
			throw new RuntimeException("属性文件不存在：" + this.getSettingFilePath());
		} catch (IOException e) {
			throw new RuntimeException("读取属性文件时出错:" + this.getSettingFilePath());
		}
		return p.getProperty("dbpath");
	}

	// 得到属性文件的物理路径

	private String getSettingFilePath() {

		if (settingfilepath != null) {
			return settingfilepath;
		}
		settingfilepath = this.getClass().getResource("/").getPath()
				+ "db-config.properties";
		settingfilepath = settingfilepath.substring(1).replace("%20", " ");
		// %20代表空格,需要将其替换
		return settingfilepath;

	}

}
