package com.pallasli.study.webroot.websocket;
import java.util.List;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/myendpoint", encoders = { MessageATextEncoder.class,
		MessageBTextEncoder.class }, decoders = { MessageTextDecoder.class }, configurator = CustomConfigurator.class)
public class MyEndpoint {
	@OnOpen
	public void open(Session s, EndpointConfig conf) {
		HandshakeRequest req = (HandshakeRequest) conf.getUserProperties().get(
				"handshakereq");
		Map<String, List<String>> headers = req.getHeaders();
		// ...
	}
}
