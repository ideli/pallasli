package com.pallasli.study.mail.manager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class STMPManager {
	/**
	 * SMTPMessage This class is a specialization of the MimeMessage class that
	 * allows you to specify various SMTP options and parameters that will be
	 * used when this message is sent over SMTP.
	 * 
	 * SMTPSSLTransport This class implements the Transport abstract class using
	 * SMTP over SSL for message submission and transport.
	 * 
	 * SMTPTransport This class implements the Transport abstract class using
	 * SMTP for message submission and transport.
	 * 
	 * 
	 * SMTPAddressFailedException This exception is thrown when the message
	 * cannot be sent.
	 * 
	 * SMTPAddressSucceededException This exception is chained off a
	 * SendFailedException when the mail.smtp.reportsuccess property is true.
	 * 
	 * SMTPSenderFailedException This exception is thrown when the message
	 * cannot be sent.
	 * 
	 * SMTPSendFailedException This exception is thrown when the message cannot
	 * be sent.The exception includes the sender's address, which the mail
	 * server rejected.
	 * 
	 * @throws MessagingException
	 */

	public void dd() throws MessagingException {

		/**
		 * Properties props = new Properties();
		 * 
		 * //Override props with any customized data
		 * 
		 * PasswordAuthentication auth = new PasswordAuthentication("manisha",
		 * "pswrd")
		 * 
		 * Session session = Session.getDefaultInstance(props, auth);
		 */
		Properties props = new Properties();
		props.put("mail.smtp.port", "587");
		Session session = Session.getInstance(props);

		Message msg = new MimeMessage(session);
		Transport tr = session.getTransport("smtp");
		tr.connect("smtphost", "username", "password");
		msg.saveChanges();
		/**
		 * public void setFrom(Address address) used to set the from header
		 * field.
		 * 
		 * public void addRecipients(Message.RecipientType type, String
		 * addresses) used to add the given address to the recipient type.
		 * 
		 * 
		 * public void setSubject(String subject) used to set the subject header
		 * field.
		 * 
		 * public void setText(String textmessage) used to set the text as the
		 * message content using text/plain MIME type.
		 */
		msg.addRecipients(Message.RecipientType.TO,
				InternetAddress.parse("ytli@163.com"));

		/**
		 * 三种预定义的地址类型是与这些值中的一个对象：
		 * 
		 * Message.RecipientType.TO
		 * 
		 * Message.RecipientType.CC
		 * 
		 * Message.RecipientType.BCC
		 **/

		tr.sendMessage(msg, msg.getAllRecipients());
		tr.close();
	}
}
