package pallasli.study.mq.rabbitmq;

import java.io.IOException;

public interface CodecFactory {

	byte[] serialize(Object obj) throws IOException;

	Object deSerialize(byte[] in) throws IOException;

}
