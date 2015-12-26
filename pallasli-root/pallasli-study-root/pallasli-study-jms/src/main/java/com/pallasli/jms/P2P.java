package com.pallasli.jms;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class P2P {
	public void producter() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://10.10.40.174:61616");
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		Destination destination = null;
		try {
			connection = connectionFactory.createConnection("zengjun", "zj");
			session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);
			destination = session.createQueue("TEST.QUEUE.ZJ_02");
			producer = session.createProducer(destination);
			// 设置消息模式，有持久与非持久的
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
			connection.start();
			double d = Math.random();
			InputStream in = new FileInputStream("D:\\2.09M.jar");
			BufferedInputStream objBufferedInputStream = new BufferedInputStream(
					in);
			int len = objBufferedInputStream.available();
			byte[] bBuffer = new byte[len];
			// 创建StreamMessage
			StreamMessage message = session.createStreamMessage();
			objBufferedInputStream.read(bBuffer);
			// 添加byte数组数据
			message.writeBytes(bBuffer);
			// 添加整型属性
			message.setIntProperty("MessageLength1", len);
			// 添加字符串属性
			message.setStringProperty("ID", String.valueOf(d));
			in.close();
			producer.send(message);
			System.out.println("发送消息成功 ID " + String.valueOf(d));
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			// 关闭资源
			ConnectionUtil.closeSession(session);
			ConnectionUtil.closeConnection(connection);
			ConnectionUtil.closeMessageProducer(producer);
		}
		System.out.println("发送结束");
	}

	public void customer() {
		// 创建连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				"tcp://10.10.40.174:61616");
		Session session = null;
		MessageConsumer consumer = null;
		Connection connection = null;
		Message message = null;
		try {
			// 访问的用户与密码
			connection = connectionFactory.createConnection("ll", "ll");
			connection.start();
			session = connection.createSession(false,
					Session.CLIENT_ACKNOWLEDGE);
			Destination destination = session.createQueue("TEST.QUEUE.ZJ_02");
			consumer = session.createConsumer(destination);
			// 无时间参数表示一直等待，直到收到消息。
			// message = consumer.receive();
			// 有时间参数表示指定时间后没有消息则结束时,如果存在消息就在取完消息后结束
			message = consumer.receive(5 * 1000);
			// 立即往下执行
			// message = consumer.receiveNoWait();
			if (message != null) {
				System.out.println("收到消息");
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					String text = textMessage.getText();
					System.out.println("TEXT:" + text);
					textMessage.acknowledge();
				} else if (message instanceof StreamMessage) {
					StreamMessage streamMessage = (StreamMessage) message;
					String strId = streamMessage.getStringProperty("ID");
					System.out.println("streammessage  ID:" + strId);
					streamMessage.acknowledge();
				}
			} else {
				System.out.println("没有收到消息");
			}
		} catch (Exception e) {
			System.out.println("发生异常\n");
			e.printStackTrace();
		} finally {
			// 关闭资源
			ConnectionUtil.closeAll(connection, session, consumer);
		}
	}
}
