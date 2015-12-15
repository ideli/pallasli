package com.pallasli.chat.service.impl;

import java.util.List;

import com.pallasli.chat.bean.ChattingRecord;
import com.pallasli.chat.bean.Group;
import com.pallasli.chat.bean.User;
import com.pallasli.chat.service.ChattingService;

public class ChattingServiceImpl implements ChattingService {

	@Override
	public void sendMsgToUser(User from, User to, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMsgToUsers(User from, List<User> to, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMsgToGroup(User from, Group to, String msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<ChattingRecord> getMsgFromUser(User from, User to) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChattingRecord> getMsgFromGroup(Group from, User to) {
		// TODO Auto-generated method stub
		return null;
	}
}
