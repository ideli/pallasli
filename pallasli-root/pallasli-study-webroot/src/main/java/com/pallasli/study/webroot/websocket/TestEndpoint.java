package com.pallasli.study.webroot.websocket;
import javax.websocket.OnError;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/testendpoint")
public class TestEndpoint {
	// ...
	@OnError
	public void error(Session session, Throwable t) {
		t.printStackTrace();
		// ...
	}
}