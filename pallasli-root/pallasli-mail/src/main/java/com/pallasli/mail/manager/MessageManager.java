package com.pallasli.mail.manager;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MessageManager {

	public static Message createMessage(Session session, String from,
			String to, String subject, String cc, String bcc, String content)
			throws AddressException, MessagingException {
		Message message = new MimeMessage(session);

		message.setFrom(new InternetAddress(from));

		message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));

		message.setRecipients(Message.RecipientType.CC,
				InternetAddress.parse(cc));

		message.setRecipients(Message.RecipientType.BCC,
				InternetAddress.parse(bcc));

		message.setSubject(subject);

		message.setText(content);
		return message;
	}

	public static Message createAttachmentMessage(Session session, String from,
			String to, String subject, String cc, String bcc, String content)
			throws AddressException, MessagingException {
		Message message = createMessage(session, from, to, subject, cc, bcc,
				content);
		// Create the message part
		BodyPart messageBodyPart = new MimeBodyPart();

		// Now set the actual message
		messageBodyPart.setText("This is message body");

		// Create a multipar message
		Multipart multipart = new MimeMultipart();

		// Set text message part
		multipart.addBodyPart(messageBodyPart);

		// Part two is attachment
		messageBodyPart = new MimeBodyPart();
		String filename = "/home/manisha/file.txt";
		DataSource source = new FileDataSource(filename);
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);

		// Send the complete message parts
		message.setContent(multipart);
		return message;
	}

	public void send(Message message) throws MessagingException {
		Transport.send(message);
	}

	public void delete(Folder folder, Message message, boolean realDelete)
			throws MessagingException {
		if (!folder.exists())
			throw new MessagingException("文件夹不存在，不允许删除邮件！");
		if (!folder.isOpen())
			throw new MessagingException("文件夹未打开，不允许删除邮件！");
		if (!message.getFolder().equals(folder))
			throw new MessagingException("文件夹不包含该邮件，不允许删除邮件！");
		message.setFlag(Flags.Flag.DELETED, true);
		folder.close(realDelete);
	}

	public void forward(Message message) {
	}

	public void replyTo(Message message) {
	}

	public void markReaded(Message message) {
	}

	public void markNotReaded(Message message) {
	}

	public void restore(Message message) {
	}

	public void deleteWithout(Message message) {
	}

	public void moveToFolder(Message message) {
	}
}
