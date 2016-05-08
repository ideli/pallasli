package com.pallasli.study.apacheftp;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.Authority;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.WritePermission;

public class FtpTest {
	public static void main(String[] args) throws FtpException {

		FtpServerFactory serverFactory = new FtpServerFactory();

		ListenerFactory factory = new ListenerFactory();

		// 设置监听端口
		factory.setPort(2221);

		// // 定义SSL 配置
		// SslConfigurationFactory ssl = new SslConfigurationFactory();
		// ssl.setKeystoreFile(new File("src/test/resources/ftpserver.jks"));
		// ssl.setKeystorePassword("password");
		//
		// // SSL 配置
		// factory.setSslConfiguration(ssl.createSslConfiguration());
		// factory.setImplicitSsl(true);

		// 默认存在一个default的监听端口，这里采用这种方式替换默认的监听端口
		serverFactory.addListener("default", factory.createListener());

		// initUsers(serverFactory);
		initUsersByFiles(serverFactory);
		FtpServer server = serverFactory.createServer();

		// 创建服务
		server.start();

	}

	public static void initUsersByFiles(FtpServerFactory serverFactory) {
		PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
		userManagerFactory
				.setFile(new File(
						"K:\\pallasli-study-apacheftp\\src\\main\\resources\\users.properties"));

		serverFactory.setUserManager(userManagerFactory.createUserManager());
	}

	public static void initUsers(FtpServerFactory serverFactory) {
		BaseUser user = new BaseUser();
		user.setName("test");
		user.setPassword("123456");
		user.setHomeDirectory("F:/ftptest");
		try {
			initAuthor(user);
			serverFactory.getUserManager().save(user);
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}

	public static void initAuthor(BaseUser user) {
		List<Authority> authorities = new ArrayList<Authority>();
		authorities.add(new WritePermission());
		user.setAuthorities(authorities);
	}
}
