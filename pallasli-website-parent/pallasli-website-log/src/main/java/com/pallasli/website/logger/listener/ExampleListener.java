package com.pallasli.website.logger.listener;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.data.mongodb.core.MongoOperations;

import com.pallasli.website.logger.entity.LogInfo;
import com.pallasli.website.logger.receive.LogToMongo;

public class ExampleListener implements MessageListener {
	@Resource(name = "mongoTemplate")
	private MongoOperations mongoOperation;

	@Override
	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {
			ObjectMessage objMessage = (ObjectMessage) message;
			try {
				Object obj = objMessage.getObject();
				LogInfo log = (LogInfo) obj;
				Object[] args = log.getArgs();

				for (Object obj1 : args) {
					System.out.println(obj1.getClass().getName());
					System.err.println(obj1.toString());

				}

				System.out.println("接收到一个ObjectMessage，包含log对象。" + log + "="
						+ mongoOperation);
				LogToMongo mongo = new LogToMongo();
				mongo.setLog(log);
				mongo.setMongoOperation(mongoOperation);
				mongo.logMongo(log);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
