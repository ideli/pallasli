package com.pallasli.study.html5.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

@ServerEndpoint(value = "/websocketTest/{relationId}/{userCode}")
public class WebSocketTest {
	private static Logger log = Logger.getLogger(WebSocketTest.class);

	@OnMessage
	public void onMessage(String message, Session session) throws IOException,
			InterruptedException {

		// Print the client message for testing purposes
		System.out.println("Received: " + message);

		// Send the first message to the client
		// session.getBasicRemote().sendText("This is the first server message");
		/**
		 * System.out.println("======"+session.getId()); // Send 3 messages to
		 * the client every 5 seconds int sentMessages = 0; while(sentMessages <
		 * 3){ Thread.sleep(5000); session.getBasicRemote().
		 * sendText("This is an intermediate server message. Count: " +
		 * sentMessages); sentMessages++; }
		 **/

		// Send a final message to the client
		// session.getBasicRemote().sendText("This is the last server message");
		String[] arrMessage = message.split(",");

		broadcast(arrMessage[0], 1234, arrMessage[1]);
	}

	@OnOpen
	public void onOpen(@PathParam("relationId") String relationId,
			@PathParam("userCode") int userCode, Session session) {
		System.out.println("onOpen：relationId--" + relationId + "userCode--"
				+ userCode);
		log.debug("Websocket Start Connecting:"
				+ webS_session.getKey(relationId, userCode));
		webS_session.put(relationId, userCode, session);
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose(@PathParam("relationId") String relationId,
			@PathParam("userCode") int userCode, Session session) {
		System.out.println("Connection closed");
		webS_session.remove(relationId, userCode);
	}

	/**
	 * 将数据传回客户端 异步的方式
	 * 
	 * @param relationId
	 * @param userCode
	 * @param message
	 */
	public void broadcast(String relationId, int userCode, String message) {
		if (webS_session.hasConnection(relationId, userCode)) {
			webS_session.get(relationId, userCode).getAsyncRemote()
					.sendText(message);
		} else {
			// throw new NullPointerException(webS_session.getKey(relationId,
			// userCode) +"Connection does not exist");
			System.out.println(webS_session.getKey(relationId, userCode)
					+ ":已经下线。");
		}

	}

}
