package com.pallasli.bpm.api;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.IdentifyService;
import com.pallasli.bpm.service.bean.UserInfo;

public class TestIdentifyService {

	@Autowired
	IdentifyService identifyService;

	@Test
	public void createUser() {
		String firstName = "";
		String lastName = "";
		String password = "";
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setPassword(password);
		boolean success = identifyService.createUser(userInfo);
		Assert.assertEquals(true, success);
	}

	@Test
	public void saveUser() {
		long id = 0;
		String firstName = "";
		String lastName = "";
		String password = "";
		UserInfo userInfo = identifyService.findUser(id);
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setPassword(password);
		boolean success = identifyService.saveUser(userInfo);
		Assert.assertEquals(true, success);

	}

	@Test
	public void deleteUser() {
		long id = 0;
		UserInfo userInfo = identifyService.findUser(id);
		boolean success = identifyService.deleteUser(userInfo);
		Assert.assertEquals(true, success);

	}

	@Test
	public void createGroup() {
	}

	@Test
	public void saveGroup() {
	}

	@Test
	public void deleteGroup() {
	}

	@Test
	public void addGroupToUser() {
	}

	@Test
	public void removeGroupFromUser() {
	}

	@Test
	public void addUserToGroup() {
	}

	@Test
	public void removeUserFromGroup() {
	}

	@Test
	public void findUser() {
		long id = 0;
		UserInfo userInfo = identifyService.findUser(id);
		Assert.assertEquals(userInfo.getId(), userInfo.getId());

	}

	@Test
	public void findGroup() {
	}

	@Test
	public void findUsersByGroup() {
	}

	@Test
	public void findGroupsByUser() {
	}
}
