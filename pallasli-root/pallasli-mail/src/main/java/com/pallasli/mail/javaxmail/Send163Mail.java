package com.pallasli.mail.javaxmail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Send163Mail {
	public static void main(String[] args) {

		try {
			execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void execute() throws Exception {
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.163.com");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props, null);
		MimeMessage msg = new MimeMessage(session);

		InternetAddress toList[] = InternetAddress.parse("user@163.com", false);
		msg.addRecipients(MimeMessage.RecipientType.TO, toList);

		InternetAddress fromAddress = new InternetAddress("user@163.com");
		msg.setFrom(fromAddress);
		msg.setSentDate(new Date());

		msg.setSubject("mail", "ISO-2022-JP");

		String txt = "hello test mail OK?";
		msg.setText(txt, "ISO-2022-JP");

		Transport transport = session.getTransport("smtp");
		transport.connect("smtp.163.com", "user@163.com", "pass");
		transport.sendMessage(msg, toList);
	}
}
