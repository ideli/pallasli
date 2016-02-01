package com.pallasli.mail.commonsemail;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

import com.pallasli.mail.MailConfig;
import com.pallasli.mail.Sender;

public class CommonsEmailSender implements Sender {

	// 纯文本
	@Override
	public String send() throws EmailException {
		String result = null;
		SimpleEmail email = new SimpleEmail();
		new MailConfig().buildConfig(email);
		// 设置发送源地址
		email.setFrom("liyongtao@atwasoft.net");
		// 设置目标地址
		email.addTo("liyongtao@atwasoft.net");
		// 设置主题
		email.setSubject("subject");
		// 设置邮件内容
		email.setMsg("msg");
		// 发送邮件
		email.send();
		result = "successful";

		return result;
	}

	// 附件
	@Override
	public String sendEnclosure() throws EmailException {
		String result = null;
		EmailAttachment emailattachment = new EmailAttachment();
		String file = "/Users/lyt1987/pallasli/gitRepo/pallasli/pallasli-root/pallasli-utils/src/test/resources/email.properties";
		// 设置附件路径
		emailattachment.setPath(file);
		// System.out.println(path);
		emailattachment.setDisposition(EmailAttachment.ATTACHMENT);
		// 附件描述
		emailattachment.setDescription("This is Smile picture");
		/*
		 * 设置附件的中文乱码问题，解决附件的中文名称 乱码问题
		 */
		// BASE64Encoder enc = new BASE64Encoder();
		// this.getName().getBytes()使用的是系统缺省的编码处理,这里是GBK
		// emailattachment
		// .setName("=?GBK?B?" + enc.encode(file.getBytes()) + "?=");

		// attachment.setName(this.getName()); //不处理字符集的话,发送的附件中文名称是乱码

		// 创建一个email
		MultiPartEmail multipartemail = new MultiPartEmail();
		new MailConfig().buildConfig(multipartemail);
		// 设置发送邮件目的地址
		multipartemail.addTo("liyongtao@atwasoft.net");
		// 设置发送又见源地址
		multipartemail.setFrom("liyongtao@atwasoft.net");
		// 设置主题
		multipartemail.setSubject("subject");
		// 设置邮件内容
		multipartemail.setMsg("msg");
		// 添加附件
		multipartemail.attach(emailattachment);
		// 发送邮件
		multipartemail.send();

		result = "The attachmentEmail send sucessful!!!";

		return result;
	}

	// HTML
	@Override
	public String sendHtml() throws EmailException, MalformedURLException {
		// 创建邮件信息
		String result = null;
		HtmlEmail email = new HtmlEmail();
		new MailConfig().buildConfig(email);
		email.addTo("liyongtao@atwasoft.net");
		email.setFrom("liyongtao@atwasoft.net");
		email.setSubject("subject");
		// embed the image and get the content id
		// URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
		// String cid = email.embed(url, "Apache logo");
		// set the html message
		// email.setHtmlMsg("<html>The apache logo - <img src=\"cid:" + cid
		// + "\"></html>");
		email.setHtmlMsg("<html>The apache logo - </html>");
		// set the alternative message
		email.setTextMsg("Your email client does not support HTML messages");
		// send the email
		email.send();

		return result;
	}

}
