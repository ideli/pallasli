package com.pallasli.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.mail.Email;

import com.pallasli.utils.FileUtils;

public class MailConfig {
	public static final int MAIL_TYPE_126 = 1;
	public static final int MAIL_TYPE_QQ = 2;
	public static final int MAIL_TYPE_163 = 3;
	public static final int MAIL_TYPE_SINA = 4;
	public static final int MAIL_TYPE_TOM = 5;
	public static final int MAIL_TYPE_263 = 6;
	public static final int MAIL_TYPE_YAHOO = 7;
	public static final int MAIL_TYPE_LIVE = 8;
	public static final int MAIL_TYPE_GMAIL = 9;
	public static final int MAIL_TYPE_ICLOUD = 10;
	public static final int MAIL_TYPE_LOACLHOST = 11;

	private static Map<String, String> hostMap = new HashMap<String, String>();
	private static Map<String, Integer> mailTypeMap = new HashMap<String, Integer>();

	static {
		// 126
		hostMap.put("smtp.126", "smtp.126.com");
		mailTypeMap.put("126", MAIL_TYPE_126);
		// qq
		hostMap.put("smtp.qq", "smtp.qq.com");
		// 163
		hostMap.put("smtp.163", "smtp.163.com");
		// sina
		hostMap.put("smtp.sina", "smtp.sina.com.cn");
		// tom
		hostMap.put("smtp.tom", "smtp.tom.com");
		// 263
		hostMap.put("smtp.263", "smtp.263.net");
		// yahoo
		hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");
		// hotmail
		hostMap.put("smtp.hotmail", "smtp.live.com");
		// gmail
		hostMap.put("smtp.gmail", "smtp.gmail.com");
		hostMap.put("smtp.port.gmail", "465");
		// icloud
		hostMap.put("smtp.icloud", "smtp.icloud.com");
		// localhost
		hostMap.put("smtp.localhost", "localhost");
		hostMap.put("smtp.port.localhost", "2025");
	}

	public static int getMailType(String mailType) {

		return MailConfig.mailTypeMap.get(mailType);
	}

	public void buildConfig(Email email) {

		Properties rc = FileUtils.getProperties("email.properties");

		// 设置主机名称
		email.setHostName(rc.getProperty("send"));
		// 设置用户名，密码
		email.setAuthentication(rc.getProperty("username"), rc.getProperty("password"));
		// 设置字符编码方式
		email.setCharset("GB2312");
	}

}
