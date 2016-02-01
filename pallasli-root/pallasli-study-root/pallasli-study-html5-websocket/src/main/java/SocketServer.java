import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/websocket/chat")
public class SocketServer {
	private static final long serialVersionUID = 1L;

	@OnOpen
	public void start(Session session) {
		System.out.println("连接成功！ " + session.getId());
	}

	@OnMessage
	public void reMessage(Session session, String str) {
		try {
			session.getBasicRemote().sendText(str + " who are you");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnError
	public void error(Session session, Throwable t) {
		t.printStackTrace();

	}

	@OnClose
	public void close() {

	}
}
