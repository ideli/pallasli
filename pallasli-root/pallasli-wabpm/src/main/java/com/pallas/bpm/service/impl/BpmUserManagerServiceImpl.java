package com.pallas.bpm.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.User;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallas.bpm.service.BpmUserManagerService;
import com.pallas.bpm.service.bean.GroupInfo;
import com.pallas.bpm.service.bean.UserInfo;

public class BpmUserManagerServiceImpl implements BpmUserManagerService {

	@Autowired
	IdentityService identityService;

	@Override
	public List<UserInfo> findUserList(String keyword, int pageSize, int pageNum) {
		List<User> list = identityService.createUserQuery().list();
		List<UserInfo> userList = new ArrayList<UserInfo>();
		for (User u : list) {
			UserInfo user = new UserInfo();
			user.setId(Long.parseLong(u.getId()));
			user.setName(u.getFirstName() + " " + u.getLastName());
		}
		return userList;
	}

	@Override
	public List<GroupInfo> findGroupList(String keyword, int pageSize,
			int pageNum) {
		return null;
	}

	@Override
	public List<UserInfo> findGroupListByUser(String userId, int pageSize,
			int pageNum) {
		return null;
	}

	@Override
	public List<GroupInfo> findUserListByGroup(String groupId, int pageSize,
			int pageNum) {
		return null;
	}

	@Override
	public boolean addGroupUser(String groupId, String userId) {
		return false;
	}

	@Override
	public boolean removeGroupUser(String groupId, String userId) {
		return false;
	}

}
