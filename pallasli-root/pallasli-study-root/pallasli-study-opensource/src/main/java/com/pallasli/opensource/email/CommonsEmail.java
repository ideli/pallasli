package com.pallasli.opensource.email;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.mail.internet.MimeUtility;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class CommonsEmail {
	public void sendEmail() {
		SimpleEmail email = new SimpleEmail();
		email.setHostName("mail.4ya.cn");
		email.setAuthentication("<username>", "<password>");
		try {
			email.addTo("martin.xus@gmail.com", "martin");
			email.setFrom("martin@4ya.cn", "martin");
			email.setSubject("测试主题");
			email.setMsg("这里是邮件内容");
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public EmailAttachment newLocalEmailAttachment() {
		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath("test/test.rar");
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("python resource");
		try {
			// 解决中文乱码问题
			attachment.setName(MimeUtility.encodeText("资源.txt"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return attachment;
	}

	public EmailAttachment newHttpEmailAttachment() {
		EmailAttachment attachment = new EmailAttachment();
		try {
			attachment.setURL(new URL(
					"http://www.smilinglibrary.org/sldoc/pics/index03.jpg"));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("微笑图书馆");
		attachment.setName("微笑图书馆");

		return attachment;
	}

	public void sendLocalEmailAttachment() {
		try {
			MultiPartEmail email = new MultiPartEmail();
			email.setHostName("mail.4ya.cn");
			email.setAuthentication("<username>", "<password>");
			email.addTo("martin.xus@gmail.com", "martin");
			email.setFrom("martin@4ya.cn", "martin");
			email.setSubject("邮件主题");
			email.setMsg("邮件内容"); // 添加附件
			email.attach(newLocalEmailAttachment());
			email.attach(newHttpEmailAttachment());

			// 发送邮件
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

	public void sendHtmlEmail() {
		try {
			// HtmlEmail!
			HtmlEmail email = new HtmlEmail();
			email.setHostName("mail.4ya.cn");
			email.setAuthentication("<username>", "<password>");
			email.addTo("martin@4ya.cn");
			email.setFrom("martin.xus@gmail.com");
			email.setSubject("主题：该邮件包括html格式内容");

			// embed the image and get the content id
			// 注意这里：embed 将帮助我们创建标签如：cid:xxx url
			URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
			String cid = email.embed(url, "Apache logo");

			/**
			 * set the html message 我们看到HtmlEmail extends
			 * Email的，它依然有setMsg()，但是这里发送的邮件包括了插入在邮件内容中的图片
			 * ，所以不能在使用了setMsg(),而要以setHtmlMsg 或setTextMsg代码
			 **/
			email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid
					+ "\"></html>");

			// set the alternative message
			email.setTextMsg("Your email client does not support HTML messages");

			// set mail
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
