package com.pallasli.study.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JndiPublisher {

	private TopicPublisher publisher;
	private TextMessage msg;

	public void publisherWeblogic() throws NamingException, JMSException {
		/* 初始化上下文对象 */
		String url = "t3://localhost:7001";
		Properties p = new Properties();
		p.put(Context.INITIAL_CONTEXT_FACTORY,
				"weblogic.jndi.WLInitialContextFactory");
		p.put(Context.PROVIDER_URL, url);
		Context ctx = new InitialContext(p);

		/* 创建一个连接工厂 */
		TopicConnectionFactory tConFactory = (TopicConnectionFactory) ctx
				.lookup("weblogic.jms.ConnectionFactory");
		/* 创建一个主题 */
		Topic messageTopic = (Topic) ctx.lookup("jms/MyTopic");
		/* 创建一个连接 */
		TopicConnection tCon = tConFactory.createTopicConnection();
		/* 创建一个会话 */
		TopicSession session = tCon.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

		/* 创建一个主题发布者，并发送消息 */
		publisher = session.createPublisher(messageTopic);
		msg = session.createTextMessage();
		publisher.publish(msg);

	}

	public void publisherTomcat() throws NamingException, JMSException {

		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
		env.put(Context.SECURITY_PRINCIPAL, "system");
		env.put(Context.SECURITY_CREDENTIALS, "manager");

		// InitialContext ctx =
		InitialContext ctx = new InitialContext(env);

		// InitialContext ctx = new InitialContext();
		// TopicConnectionFactory factory = (TopicConnectionFactory) ctx
		// .lookup(topicFactory);
		TopicConnectionFactory factory = new ActiveMQConnectionFactory(
				"tcp://localhost:61616");
		TopicConnection connection = factory.createTopicConnection();
		TopicSession pubSession = connection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

	}

}
