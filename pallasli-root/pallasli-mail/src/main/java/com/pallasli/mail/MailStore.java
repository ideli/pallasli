package com.pallasli.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Store;

public class MailStore {

	public static final int MAIL_STORE_TYPE_POP3S = 1;
	public static final int MAIL_STORE_TYPE_IMAPS = 2;

	private static Map<String, Integer> mailTypeMap = new HashMap<String, Integer>();
	static {
		mailTypeMap.put("pop3s", MAIL_STORE_TYPE_POP3S);
		mailTypeMap.put("imaps", MAIL_STORE_TYPE_IMAPS);
	}

	private Store store;

	private MailStore() {

	}

	private MailStore(Store store) {
		this.store = store;
	}

	public MailStore openStore(Session session, String mailStoreType)
			throws NoSuchProviderException {
		Store store = session.getStore(mailStoreType);
		return new MailStore(store);
	}

	public void closeStore() throws MessagingException {
		this.store.close();
	}
}
