package com.pallasli.study.webroot.websocket;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageATextEncoder implements Encoder.Text<MessageA> {
	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

	@Override
	public String encode(MessageA msgA) throws EncodeException {
		// Access msgA's properties and convert to JSON text...
		// return msgAJsonString;
		return null;
	}
}
