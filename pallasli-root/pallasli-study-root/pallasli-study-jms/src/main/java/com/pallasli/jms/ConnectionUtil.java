package com.pallasli.jms;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQTopicPublisher;

public class ConnectionUtil {

	public static void closeSession(Session session) {
		try {
			session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeMessageProducer(MessageProducer producer) {
		try {
			producer.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeAll(Connection connection, Session session,
			MessageConsumer consumer) {
		closeSession(session);
		closeConnection(connection);
		try {
			consumer.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void closeTopicPublisher(ActiveMQTopicPublisher publisher) {
		try {
			publisher.close();
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
