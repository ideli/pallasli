package com.pallasli.website.logger.receive;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.fusesource.mq.leveldb.RecordLog.LogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.jms.core.JmsTemplate;

public class LogReceive {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name = "mongoTemplate")
	private MongoOperations mongoOperation;

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public MongoOperations getMongoOperation() {
		return mongoOperation;
	}

	public void setMongoOperation(MongoOperations mongoOperation) {
		this.mongoOperation = mongoOperation;
	}

	public void receive11() throws JMSException {
		Message mm = jmsTemplate.receive();
		ObjectMessage objMessage = (ObjectMessage) mm;
		LogInfo log = (LogInfo) objMessage.getObject();
		System.out.println(log);

	}
}
