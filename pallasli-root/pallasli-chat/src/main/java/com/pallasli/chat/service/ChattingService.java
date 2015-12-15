package com.pallasli.chat.service;

import java.util.List;

import com.pallasli.chat.bean.ChattingRecord;
import com.pallasli.chat.bean.Group;
import com.pallasli.chat.bean.User;

public interface ChattingService {
	public void sendMsgToUser(User from, User to, String msg);

	public void sendMsgToUsers(User from, List<User> to, String msg);

	public void sendMsgToGroup(User from, Group to, String msg);

	public List<ChattingRecord> getMsgFromUser(User from, User to);

	public List<ChattingRecord> getMsgFromGroup(Group from, User to);
}
