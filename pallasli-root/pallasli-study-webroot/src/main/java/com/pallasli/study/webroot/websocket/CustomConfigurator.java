package com.pallasli.study.webroot.websocket;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

public class CustomConfigurator extends ServerEndpointConfig.Configurator {
	@Override
	public void modifyHandshake(ServerEndpointConfig conf,
			HandshakeRequest req, HandshakeResponse resp) {
		conf.getUserProperties().put("handshakereq", req);
	}
}
