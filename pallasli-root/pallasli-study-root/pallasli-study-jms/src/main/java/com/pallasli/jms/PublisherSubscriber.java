package com.pallasli.jms;

import java.io.FileInputStream;

import javax.jms.DeliveryMode;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.jms.TopicConnection;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQTopicPublisher;
import org.apache.activemq.ActiveMQTopicSession;
import org.apache.activemq.ActiveMQTopicSubscriber;
import org.apache.activemq.command.ActiveMQTopic;

public class PublisherSubscriber implements MessageListener {
	public void publisher() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://10.10.40.174:61616");
		TopicConnection connection = null;
		ActiveMQTopicSession session = null;
		ActiveMQTopicPublisher publisher = null;
		ActiveMQTopic topic = null;
		try {
			connection = connectionFactory.createTopicConnection("zengjun",
					"zj");
			session = (ActiveMQTopicSession) connection.createTopicSession(
					false, Session.AUTO_ACKNOWLEDGE);
			topic = (ActiveMQTopic) session.createTopic("TEST.topic.zj");
			publisher = (ActiveMQTopicPublisher) session.createPublisher(topic);
			publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
			connection.start();
			int flag = 2;
			if (flag == 1) {
				TextMessage messageText = session.createTextMessage();
				messageText.setText("tipic:" + System.currentTimeMillis());
				publisher.publish(messageText);
			} else {
				StreamMessage messageStream = session.createStreamMessage();
				FileInputStream fi = new FileInputStream("D:\\JavaXYQ.zip");
				byte[] btyes = new byte[fi.available()];
				fi.read(btyes);
				messageStream.writeBytes(btyes);
				publisher.publish(messageStream);
				fi.close();
			}

			System.out.println("Topic消息发送成功");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionUtil.closeSession(session);
			ConnectionUtil.closeConnection(connection);
			ConnectionUtil.closeTopicPublisher(publisher);
		}
	}

	public void subscriber() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://10.10.40.174:61616");
		TopicConnection connection = null;
		ActiveMQTopicSession session = null;
		ActiveMQTopic topic = null;
		ActiveMQTopicSubscriber subscriber = null;
		try {
			connection = connectionFactory.createTopicConnection("zengjun",
					"zj");
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

	public void onMessage(Message message) {
		System.out.println("收到消息");
		try {
			if (message != null) {
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					System.out.println(textMessage.getText());
				} else if (message instanceof StreamMessage) {
					System.out.println("收到steam消息");
				}
			} else {
				System.out.println("没有收到消息");
			}
			Thread.sleep(1 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
