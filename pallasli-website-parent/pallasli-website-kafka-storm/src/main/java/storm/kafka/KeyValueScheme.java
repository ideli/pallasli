package storm.kafka;

import java.util.List;

import org.apache.storm.spout.Scheme;

public interface KeyValueScheme extends Scheme {

	public List<Object> deserializeKeyAndValue(byte[] key, byte[] value);

}
