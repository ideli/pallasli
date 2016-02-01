package com.pallasli.mail;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;
import org.junit.Test;

import com.pallasli.mail.commonsemail.CommonsEmailSender;

public class SenderTest {
	@Test
	public void test() {
		CommonsEmailSender sender = new CommonsEmailSender();
		try {
			sender.send();
			sender.sendEnclosure();
			sender.sendHtml();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}
}
