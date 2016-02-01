package com.pallasli.mail.javaxmail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.pallasli.mail.EmailAuthenticator;

public class Mail {
	private String mailServer, From, To, mailSubject, MailContent;

	private String username, password;

	private Session mailSession;

	private Properties prop;

	private Message message;

	// Authenticator auth;//认证
	public Mail() {
		// 设置邮件相关
		username = "admin";
		password = "123456";
		mailServer = "localhost";
		From = "admin@localhost";
		To = "admin@localhost";
		mailSubject = "Hello Scientist";
		MailContent = "How are you today!";
	}

	public void send() {
		EmailAuthenticator mailauth = new EmailAuthenticator(username, password);
		// 设置邮件服务器
		prop = System.getProperties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", mailServer);
		prop.put("mail.smtp.port", "2025");
		// 产生新的Session服务
		mailSession = mailSession.getDefaultInstance(prop, mailauth);
		message = new MimeMessage(mailSession);

		try {
			message.setFrom(new InternetAddress(From)); // 设置发件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					To));// 设置收件人
			message.setSubject(mailSubject);// 设置主题
			message.setContent(MailContent, "text/plain");// 设置内容
			message.setSentDate(new Date());// 设置日期
			Transport tran = mailSession.getTransport("smtp");
			tran.connect(mailServer, username, password);
			tran.send(message, message.getAllRecipients());
			tran.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Mail mail;
		mail = new Mail();
		System.out.println("sending......");
		mail.send();
		System.out.println("finished!");
	}

}
