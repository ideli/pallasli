package com.pallasli.study.mail.james;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.mailet.MailAddress;
import org.apache.mailet.base.GenericMailet;

public class BizMaillet extends GenericMailet {

	@Override
	public void service(org.apache.mailet.Mail mail) {
		MailAddress ma = mail.getSender();
		System.out.println("sender:" + ma.toInternetAddress().toString());
		try {
			MimeMessage mm = mail.getMessage();
			/**
			 * MimeMultipart mmp=(MimeMultipart) mm.getContent(); String
			 * testMessage = "Hello "+mmp.getBodyPart(0).getContent();
			 */
			System.out.println("content:" + mm.getContent().toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}