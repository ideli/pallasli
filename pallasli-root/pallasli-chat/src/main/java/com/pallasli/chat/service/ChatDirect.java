package com.pallasli.chat.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.pallasli.bean.User;
import com.pallasli.chat.bean.TestVO;

public class ChatDirect {
	static String chat_words = "";

	/**
	 * T_CHAT_HISTORY
	 * 
	 * @param user
	 * @return
	 */
	public String getHistory(User fromUser, User toUser, String fromDate) {

		// select * from T_CHAT_HISTORY

		return "";
	}

	/**
	 * T_CHAT to T_CHAT_HISTORY
	 * 
	 * @param user
	 * @return
	 */

	public String getNewRecord(User fromUser, User toUser) {

		// select * from T_CHAT
		// where
		// user_from_id = fromUser.getId()
		// and user_to_id = toUser.getId()
		// order by chat_time

		// insert into T_CHAT_HISTORY() select () from T_CHAT
		// where
		// user_from_id = fromUser.getId()
		// and user_to_id = toUser.getId()

		// delete from T_CHAT
		// where
		// user_from_id = fromUser.getId()
		// and user_to_id = toUser.getId()

		return "";
	}

	/**
	 * T_CHAT
	 * 
	 * @param user
	 * @return
	 */

	public String sendMsg(User user) {

		return "";
	}

	/**
	 * T_CHAT to T_CHAT_HISTORY
	 * 
	 * @param user
	 * @return
	 */

	public String getMsg(User user) {

		return "";
	}

	// 注意注解

	public String chat(TestVO vo) {// 这里可以直接使用对象来接收值了，恩，很方便的说
		String user = vo.getUser();
		String chat = vo.getChat();
		if (user != null) {
			chat_words = chat_words + "<p>用户 " + user + " 在 "
					+ formatDate(new Date()) + " 说: " + chat + "</p>";
		}
		return chat_words;
	}

	public String multiply(TestVO vo) {// 这里可以直接使用对象来接收值了，恩，很方便的说
		String user = vo.getUser();
		String chat = vo.getChat();
		if (user != null) {
			chat_words = chat_words + "<p>用户 " + user + " 在 "
					+ formatDate(new Date()) + " 说: " + chat + "</p>";
		}
		return chat_words;
	}

	// direct polling注释
	public String chatRoom(Map<String, String> params) {
		// 测试polling获得前台的传值
		// System.out.println(params.get("polling_date"));
		return chat_words;
	}

	private String formatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(date);
	}
}
