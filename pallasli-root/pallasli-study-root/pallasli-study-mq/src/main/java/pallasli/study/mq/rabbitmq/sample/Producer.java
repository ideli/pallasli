package pallasli.study.mq.rabbitmq.sample;

import java.io.IOException;
import java.io.Serializable;

import org.springframework.amqp.utils.SerializationUtils;

/**
 * 
 * 功能概要：消息生产者
 * 
 * @author linbingwen
 * @since 2016年1月11日
 */
public class Producer extends EndPoint {

	public Producer(String endPointName) throws IOException {
		super(endPointName);
	}

	public void sendMessage(Serializable object) throws IOException {
		channel.basicPublish("", endPointName, null, SerializationUtils.serialize(object));
	}
}
