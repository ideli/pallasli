package com.pallasli.mail;

public class Parser {
	public int getMailType(String mailAdress) {
		if (MailUtils.checkMailAdress(mailAdress)) {
			String[] arr1 = mailAdress.split("@");
			String[] arr2 = arr1[1].split(".");
			return MailConfig.getMailType(arr2[0]);
		}
		return 0;
	};
}
