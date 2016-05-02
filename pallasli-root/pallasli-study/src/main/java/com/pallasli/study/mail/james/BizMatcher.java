package com.pallasli.study.mail.james;

import javax.mail.MessagingException;

import org.apache.mailet.MailAddress;
import org.apache.mailet.base.GenericRecipientMatcher;

public class BizMatcher extends GenericRecipientMatcher {

	public boolean matchRecipient(MailAddress recipient) throws MessagingException {
		// 只接受邮件地址包含鸣人的邮件
		if (recipient.getUser().indexOf("mingren") != -1) {
			return true;
		}
		return false;
	}
}