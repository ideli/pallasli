package com.pallasli.study.webroot.websocket;

import java.io.IOException;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.log4j.Logger;

/**
 * @ ServerEndpoint
 * 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问URL地址 。
 * 
 * onOpen 和 onClose 方法分别被@OnOpen和@OnClose
 * 所注解。这两个注解的作用不言自明：他们定义了当一个新用户连接和断开的时候所调用的方法。
 * 
 * onMessage 方法被@OnMessage所注解。这个注解定义了当服务器接收到客户端发送的消息时所调用的方法。注意： 这个方法可能包含一个javax
 * .websocket.Session可选参数（在我们的例子里就是session参数）。如果有这个参数
 * ，容器将会把当前发送消息客户端的连接Session注入进去。
 * 
 * 本例中我们仅仅是将客户端消息内容打印出来，然后首先我们将发送一条开始消息，之后间隔5秒向客户端发送1条测试消息，共发送3次
 * ，最后向客户端发送最后一条结束消息。
 * 
 * @author lyt
 * 
 */
@ServerEndpoint(value = "/websocketTest/{relationId}/{userCode}")
public class WebSocketTest {
	private static Logger log = Logger.getLogger(WebSocketTest.class);

	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {

		// Print the client message for testing purposes
		System.out.println("Received: " + message);

		// Send the first message to the client
		// session.getBasicRemote().sendText("This is the first server
		// message");
		/**
		 * System.out.println("======"+session.getId()); // Send 3 messages to
		 * the client every 5 seconds int sentMessages = 0; while(sentMessages <
		 * 3){ Thread.sleep(5000); session.getBasicRemote(). sendText(
		 * "This is an intermediate server message. Count: " + sentMessages);
		 * sentMessages++; }
		 **/

		// Send a final message to the client
		// session.getBasicRemote().sendText("This is the last server message");
		String[] arrMessage = message.split(",");

		broadcast(arrMessage[0], 1234, arrMessage[1]);
	}

	@OnOpen
	public void onOpen(@PathParam("relationId") String relationId, @PathParam("userCode") int userCode,
			Session session) {
		System.out.println("onOpen：relationId--" + relationId + "userCode--" + userCode);
		log.debug("Websocket Start Connecting:" + webS_session.getKey(relationId, userCode));
		webS_session.put(relationId, userCode, session);
		System.out.println("Client connected");
	}

	@OnClose
	public void onClose(@PathParam("relationId") String relationId, @PathParam("userCode") int userCode,
			Session session) {
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
			webS_session.get(relationId, userCode).getAsyncRemote().sendText(message);
		} else {
			// throw new NullPointerException(webS_session.getKey(relationId,
			// userCode) +"Connection does not exist");
			System.out.println(webS_session.getKey(relationId, userCode) + ":已经下线。");
		}

	}

}
