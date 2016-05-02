package com.pallasli.study.mail.james;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

//发布－Matcher 和 Mailet以及config.xml
//1.发布jar文件
//        我们把这个jar文件发布到C:\james\apps\james\SAR-INF\lib。
//        注意：如果没有找到相关目录，则需要先启动一遍james,相关的文件夹才会被创建。还有一点需要特别说明：lib目录是通过我们手动创建的。
// 2.将Matcher 和 Mailet发布到config.xml中，config.xml在james\apps\james\SAR-INF\下
//   1）我们首先找到如下内容 
//<mailetpackages>  
//  <mailetpackage>org.apache.james.transport.mailetsmailetpackage>  
//  <mailetpackage>org.apache.james.transport.mailets.smimemailetpackage>  
//mailetpackages>  
//<matcherpackages>  
//  <matcherpackage>org.apache.james.transport.matchersmatcherpackage>  
//  <matcherpackage>org.apache.james.transport.matchers.smimematcherpackage>  
//matcherpackages>  
//  2）加入包声明 
//<mailetpackages>  
//  <mailetpackage>com.yy.jamesstudymailetpackage>  
//  <mailetpackage>org.apache.james.transport.mailetsmailetpackage>  
//  <mailetpackage>org.apache.james.transport.mailets.smimemailetpackage>  
//mailetpackages>  
//
//<matcherpackages>  
//  <matcherpackage>com.yy.jamesstudy matcherpackage>  
//  <matcherpackage>org.apache.james.transport.matchersmatcherpackage>  
//  <matcherpackage>org.apache.james.transport.matchers.smimematcherpackage>  
//matcherpackages>  
//
//3）加入 Matcher 和 Mailet的关联关系
// 找到  元素在它的下面加入
//xml 代码
//<mailet match="BizMatcher" class="BizMaillet"/>  
//Mailet元素代表了一个matcher和一个mailet的组合。Match属性：是指matcher的类名。而class 属性：是指mailet的类名。
//最后别忘了，保存退出。并且重新启动james服务器。
//测试－ 验证我们的mail应用程序
//我们主要通过mail类来测试我们的应用。还记得我们刚才写的那个mail类吗？！在那个类中我们初始化了相关的信息.
//               username = "kakaxi";
//               password = "kakaxi";
//               mailServer = "localhost";
//               From = "kakaxi@localhost";
//               To = "mingren@localhost";
//               mailSubject = "Hello Scientist";
//               MailContent = "How are you today!";
//发件人是卡卡西，收件人是mingren.这两个用户我们在前面都已经创建完毕。我们用他们来测试james的邮件收发以及mailet api的应用。
//根据需求假设我们发给james 服务器（这里是james的默认配置：localhost）的邮件的收件人是鸣人。那么我们就能通过matcher监测到这封邮件，并且调用相应的mailet来进行处理。由mailet打印出相应的邮件发送者和正文。运行Mail类后得到
//Using PHOENIX_HOME:   C:\james
//Using PHOENIX_TMPDIR: C:\james\temp
//Using JAVA_HOME:      C:\j2sdk1.4.2_02
//Phoenix 4.2
//James Mail Server 2.3.1
//Remote Manager Service started plain:4555
//POP3 Service started plain:110
//SMTP Service started plain:25
//NNTP Service started plain:119
//FetchMail Disabled
//
//sender:kakaxi@localhost
//content:How are you today!
//总结
//最终我们看到发送者和正文的信息。That’s all ! 大功告成。
public class Mail {
	private String mailServer, From, To, mailSubject, MailContent;

	private String username, password;

	private Session mailSession;

	private Properties prop;

	private Message message;

	// Authenticator auth;//认证
	public Mail() {
		// 设置邮件相关
		username = "admin";
		password = "123456";
		mailServer = "localhost";
		From = "admin@pallasli.com";
		To = "admin2@pallasli.com";
		mailSubject = "Hello Scientist";
		MailContent = "How are you today!";
	}

	public void send() {
		EmailAuthenticator mailauth = new EmailAuthenticator(username, password);
		// 设置邮件服务器
		prop = System.getProperties();
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.host", mailServer);
		prop.put("mail.smtp.port", 2025);
		// 产生新的Session服务
		mailSession = mailSession.getDefaultInstance(prop, mailauth);
		message = new MimeMessage(mailSession);

		try {
			message.setFrom(new InternetAddress(From)); // 设置发件人
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(To));// 设置收件人
			message.setSubject(mailSubject);// 设置主题
			message.setContent(MailContent, "text/plain");// 设置内容
			message.setSentDate(new Date());// 设置日期
			Transport tran = mailSession.getTransport("smtp");
			tran.connect(mailServer, username, password);
			tran.send(message, message.getAllRecipients());
			tran.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fetch(String pop3Host, String storeType, String user, String password) {
		try {
			// create properties field
			Properties properties = new Properties();
			properties.put("mail.store.protocol", "pop3");
			properties.put("mail.pop3.host", pop3Host);
			properties.put("mail.pop3.port", "2110");
			properties.put("mail.pop3.starttls.enable", "true");
			Session emailSession = Session.getDefaultInstance(properties);
			// emailSession.setDebug(true);

			// create the POP3 store object and connect with the pop server
			Store store = emailSession.getStore("pop3");

			store.connect(pop3Host, user, password);

			// create the folder object and open it
			Folder emailFolder = store.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

			// retrieve the messages from the folder in an array and print it
			Message[] messages = emailFolder.getMessages();
			System.out.println("messages.length---" + messages.length);

			for (int i = 0; i < messages.length; i++) {
				Message message = messages[i];
				System.out.println("---------------------------------");
				// writePart(message);
				System.out.println(message.getContent());
				String line = reader.readLine();
				if ("YES".equals(line)) {
					message.writeTo(System.out);
				} else if ("QUIT".equals(line)) {
					break;
				}
			}

			// close the store and folder objects
			emailFolder.close(false);
			store.close();

		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Mail mail;
		mail = new Mail();
		System.out.println("sending......");
		mail.send();
		System.out.println("finished!");
		System.out.println("fetching......");
		String host = "localhost";// change accordingly
		String mailStoreType = "pop3";
		String username = "admin2@pallasli.com";// change accordingly
		String password = "123456";// change accordingly

		// Call method fetch
		fetch(host, mailStoreType, username, password);

	}
}

class EmailAuthenticator extends Authenticator {
	private String m_username = null;

	private String m_userpass = null;

	void setUsername(String username) {
		m_username = username;
	}

	void setUserpass(String userpass) {
		m_userpass = userpass;
	}

	public EmailAuthenticator(String username, String userpass) {
		super();
		setUsername(username);
		setUserpass(userpass);
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(m_username, m_userpass);
	}
}