package com.pallasli.mail.javaxmail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class FetchMail {

	public static void main(String[] args) {
		String protocol = "pop3";
		boolean isSSL = false;
		String host = "localhost";
		int port = 2110;
		String username = "admin@localhost";
		String password = "123456";

		Properties props = new Properties();
		props.put("mail.pop3.ssl.enable", isSSL);
		props.put("mail.pop3.host", host);
		props.put("mail.pop3.port", port);

		Session session = Session.getDefaultInstance(props);

		Store store = null;
		Folder folder = null;
		try {
			store = session.getStore(protocol);
			store.connect(username, password);

			folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			int size = folder.getMessageCount();
			Message message = folder.getMessage(size);

			String from = message.getFrom()[0].toString();
			String subject = message.getSubject();
			Date date = message.getSentDate();

			System.out.println("From: " + from);
			System.out.println("Subject: " + subject);
			System.out.println("Date: " + date);
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} finally {
			try {
				if (folder != null) {
					folder.close(false);
				}
				if (store != null) {
					store.close();
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		System.out.println("接收完毕！");
	}
}
