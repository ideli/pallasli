package pallasli.study.mq.rabbitmq;

import java.io.IOException;

import org.springframework.amqp.AmqpException;

public class SendRefuseException extends Exception {

	public SendRefuseException(String string) {
	}

	public SendRefuseException(IOException e) {
	}

	public SendRefuseException(String string, AmqpException e) {
	}

}
