package com.pallasli.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ChatTest implements MessageListener {
	private TopicSession pubSession;
	private TopicSession subSession;
	private TopicPublisher publisher;
	// private TopicSubscriber subscriber;
	private TopicConnection connection;

	// private String username;

	public ChatTest(String topicFactory, String topicName, String userName) {
		try {

			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			env.put(Context.SECURITY_PRINCIPAL, "system");
			env.put(Context.SECURITY_CREDENTIALS, "manager");

			// InitialContext ctx =
			new InitialContext(env);

			// InitialContext ctx = new InitialContext();
			// TopicConnectionFactory factory = (TopicConnectionFactory) ctx
			// .lookup(topicFactory);
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(
					"tcp://localhost:61616");
			connection = factory.createTopicConnection();
			pubSession = connection.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);
			subSession = connection.createTopicSession(false,
					Session.AUTO_ACKNOWLEDGE);

			// Topic p = (Topic) ctx.lookup(topicName);
			// Topic p = new ActiveMQTopic();
			System.out.println(pubSession);
			System.out.println(subSession);
			publisher = pubSession.createPublisher(null);
			// subscriber = subSession.createSubscriber(null);
			// subscriber = subSession.createSubscriber(null, null, true);
			// subscriber.setMessageListener(this);
			connection.start();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void writeMessage(String msg) {
		try {
			TextMessage textMsg = pubSession.createTextMessage();
			textMsg.setText(msg);
			publisher.publish(textMsg);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public void onMessage(Message arg0) {
		TextMessage textMsg = (TextMessage) arg0;
		try {
			System.out.println(textMsg.getText());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// java.naming.factory.initial=org.apache.activemq.jndi.ActiveMQInitialContextFactory
		// connectionFactoryNames=TopicCF
		// topic.topic1 = jms.topic1;
		ChatTest test = new ChatTest("java.naming.factory.initial",
				"jms.topic1", "admin");
		test.writeMessage("chating");

	}
}
