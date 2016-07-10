package pallasli.study.mq.kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class KafkaProceducerCluster extends Thread {

	private String topic;

	public KafkaProceducerCluster(String topic) {
		super();
		this.topic = topic;
	}

	@Override
	public void run() {
		Producer producer = createProducer();
		int i = 0;
		while (true) {
			producer.send(new KeyedMessage<Integer, String>(topic, "message: " + i++));
			producer.send(new KeyedMessage<Integer, String>(topic + "2", "message: " + i++));
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Producer createProducer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "127.0.0.1:2181,127.0.0.1:2183,127.0.0.1:2182");// 声明zk
		properties.put("serializer.class", StringEncoder.class.getName());
		properties.put("metadata.broker.list", "127.0.0.1:9092,127.0.0.1:9094,127.0.0.1:9093");// 声明kafka
																								// broker
		return new Producer<Integer, String>(new ProducerConfig(properties));
	}

	public static void main(String[] args) {
		new KafkaProceducerCluster("test").start();// 使用kafka集群中创建好的主题 test

	}

}
