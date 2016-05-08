package com.pallasli.website.logger.utils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息侦听器会对队列（或话题）进行“侦听”。一旦有新消息添加到了队列中，onMessage 方法就会得到调用。
 * 
 * 1: 检查从队列中拿到的对象是否是ObjectMessage的实例
 *
 * 2: 从消息中提取出LoggingEventWrapper对象
 *
 * 3: 调用服务方法将日志持久化
 * 
 * @author lyt1987
 *
 */
@Component
public class LogQueueListener implements MessageListener {
	public static Logger logger = Logger.getLogger(LogQueueListener.class);

	@Autowired
	private ILoggingService loggingService;

	@Override
	public void onMessage(final Message message) {
		if (message instanceof ObjectMessage) {
			try {
				final LoggingEventWrapper loggingEventWrapper = (LoggingEventWrapper) ((ObjectMessage) message)
						.getObject();
				loggingService.saveLog(loggingEventWrapper);
			} catch (final JMSException e) {
				logger.error(e.getMessage(), e);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
}