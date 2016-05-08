package com.pallasli.website.logger.utils;

import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Appender;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

/**
 * JMSQueue appender is a log4j appender that writes LoggingEvent to a queue.
 * 
 * 1：We我们实现了的Log4J日志追加器接口，该接口要求我们实现三个方法：requiresLayout,
 * close和append。我们将暂时简化处理过程，实现所需的append方法。在对logger进行调用时这个方法就会被调用。
 * 
 * 2: log4j将一个LoggingEvent对象作为参数对append方法进行调用，这个LoggingEvent对象表示了对logger的一次调用，
 * 它封装了每一个日志项的所有信息。
 *
 * 3：将指向JMS的uri作为参数，创建一个连接工厂对象，在我们的情况下，该uri指向的是我们的ActiveMQ服务器。
 *
 * 4: 我们同JMS服务器建立一个连接和会话。会话有多种打开模式。在Auto_Acknowledge模式的会话中，消息的应答会自动发生。
 * Client_Acknowledge
 * 模式下，客户端需要对消息的接收和/或处理进行显式地应答。另外还有两种其它的模式。有关细节，请参考文档http://download.oracle.com/
 * javaee/1.4/api/javax/jms/Session.html
 *
 * 5: 创建一个队列。将队列的名字作为参数发送给连接
 * 
 * 6: 我们将发送模式设置为Non_Persistent。另一个可选的模式是Persistent
 * ，在这种模式下，消息会持久化到一个持久性存储系统中。持久化模式会降低系统速度，但能增加了消息传递的可靠性。
 *
 * 7: 这行我们做了很多事。首先我将一个LoggingEvent对象封装到了一个LoggingEventWrapper对象之中。
 * 这么做是因为LoggingEvent对象有一些属性不支持序列化，另外还有一个原因是我想记录一些额外的信息，比如IP地址和主机名。接下来，
 * 使用JMS的会话对象，我们把一个对象（LoggingEventWrapper对象）做好了发送前的准备。
 *
 * 8: 我将该对象发送到了队列中。
 * 
 * 
 * @author faheem
 *
 */
public class JMSQueueAppender extends AppenderSkeleton implements Appender {

	private static Logger logger = Logger.getLogger("JMSQueueAppender");

	private String brokerUri;
	private String queueName;

	@Override
	public void close() {

	}

	@Override
	public boolean requiresLayout() {
		return false;
	}

	@Override
	protected synchronized void append(LoggingEvent event) {

		try {

			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(this.brokerUri);

			// Create a Connection
			javax.jms.Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(this.queueName);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			ObjectMessage message = session.createObjectMessage(new LoggingEventWrapper(event));

			// Tell the producer to send the message
			producer.send(message);

			// Clean up
			session.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setBrokerUri(String brokerUri) {
		this.brokerUri = brokerUri;
	}

	public String getBrokerUri() {
		return brokerUri;
	}

	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueName() {
		return queueName;
	}
}