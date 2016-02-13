package com.pallasli.study.webroot.websocket;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageBTextEncoder implements Encoder.Text<MessageB> {
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(MessageB msgB) throws EncodeException {
		// Access msgA's properties and convert to JSON text...
		// return msgBJsonString;
		return null;
	}
}
