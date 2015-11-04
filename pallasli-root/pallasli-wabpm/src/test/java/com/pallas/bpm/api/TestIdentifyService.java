package com.pallas.bpm.api;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallas.bpm.service.IdentifyService;
import com.pallas.bpm.service.bean.UserInfo;

public class TestIdentifyService {

	@Autowired
	IdentifyService identifyService;
	
	@Test
	public void createUser() {
		String firstName="";
		String lastName="";
		String password="";
		UserInfo userInfo=new UserInfo();
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setPassword(password);
		boolean success=identifyService.createUser(userInfo); 
	}

	@Test
	public void saveUser() {
		long id=0;
		String firstName="";
		String lastName="";
		String password="";
		UserInfo userInfo=identifyService.findUser(id);
		userInfo.setFirstName(firstName);
		userInfo.setLastName(lastName);
		userInfo.setPassword(password);
		boolean success=identifyService.saveUser(userInfo); 
	}

	@Test
	public void deleteUser() {
		long id=0;
		UserInfo userInfo=identifyService.findUser(id);
		boolean success=identifyService.deleteUser(userInfo); 
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
		long id=0;
		UserInfo userInfo=identifyService.findUser(id);
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
