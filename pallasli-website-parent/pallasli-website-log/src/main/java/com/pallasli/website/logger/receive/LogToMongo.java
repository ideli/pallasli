package com.pallasli.website.logger.receive;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.jms.core.JmsTemplate;

import com.pallasli.website.logger.entity.LogInfo;

public class LogToMongo {

	private LogInfo log;

	@Autowired
	private JmsTemplate jmsTemplate;
	@Resource(name = "mongoTemplate")
	private MongoOperations mongoOperation;

	public LogInfo getLog() {
		return log;
	}

	public void setLog(LogInfo log) {
		this.log = log;
	}

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

	public void logMongo(LogInfo log) {
		System.out.println("插入数据");
		System.out.println(mongoOperation);
		mongoOperation.save(log);
	}
}
