package com.pallasli.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class MailConnection {
	public Session getSendSession(final String username, final String password)
			throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(new File("mail.smtp.properties")));
		// Session session = Session.getDefaultInstance(props);
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}
}
