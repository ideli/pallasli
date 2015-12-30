package com.pallasli.study.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQTopicSession;
import org.apache.activemq.ActiveMQTopicSubscriber;
import org.apache.activemq.command.ActiveMQTopic;

public class Subscriber implements MessageListener {
	public void subscriber() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://127.0.0.1:61616");
		TopicConnection connection = null;
		ActiveMQTopicSession session = null;
		ActiveMQTopic topic = null;
		ActiveMQTopicSubscriber subscriber = null;
		try {
			connection = connectionFactory.createTopicConnection("zengjuns",
					"zjs");
			connection.setClientID("client_ID_test");
			connection.start();
			session = (ActiveMQTopicSession) connection.createTopicSession(
					false, Session.AUTO_ACKNOWLEDGE);
			topic = (ActiveMQTopic) session.createTopic("TEST.topic.zj");
			// 创建持久订阅
			subscriber = (ActiveMQTopicSubscriber) session
					.createDurableSubscriber(topic, "Subscriber_name_test");
			subscriber.setMessageListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ConnectionUtil.closeSession(session);
			// ConnectionUtil.closeConnection(connection);
			// ConnectionUtil.closeTopicSubscriber(subscriber);
		}
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("收到消息");
		try {
			if (message != null) {
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println(textMessage.getText());
				} else if (message instanceof StreamMessage) {
					System.out.println("收到steam消息");
					System.out.println(message.getJMSMessageID());
					StreamMessage sm = (StreamMessage) message;
					byte[] bytes = new byte[1024];
					sm.readBytes(bytes);
					System.out.println(new String(bytes));
				}
			} else {
				System.out.println("没有收到消息");
			}
			Thread.sleep(1 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Subscriber().subscriber();
	}
}
