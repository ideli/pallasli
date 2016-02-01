package com.pallasli.mail;

import java.net.MalformedURLException;

import org.apache.commons.mail.EmailException;

public interface Sender {

	// 纯文本
	public String send() throws EmailException;

	// 附件
	public String sendEnclosure() throws EmailException;

	// HTML
	public String sendHtml() throws EmailException, MalformedURLException;

}
