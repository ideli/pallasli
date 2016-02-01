package com.pallasli.mail;

import javax.mail.Flags;
import javax.mail.Message;
import javax.mail.MessagingException;

public class MailOperation {
	public void deleteMail(Message message) throws MessagingException {
		message.setFlag(Flags.Flag.DELETED, true);
	}

	public void replyMailTo() {

	}

	public void sendMail() {

	}

	public void sendBackMail() {

	}

	public void forwardMail() {

	}

	public void openFolder() {

	}
}
