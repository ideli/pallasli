package pallasli.study.mq.rabbitmq;

public interface EventProcesser {
	public void process(Object e);
}