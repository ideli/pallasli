package com.pallasli.study.bpm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class TaskInfoServiceTest extends BaseTest {
	@Test
	public void listTaskInfos() {
		taskInfoService.listTaskInfos();
	}

	@Test
	public void listDuaTimeoutTask() {
		taskInfoService.listDuaTimeoutTask("");
	}

	@Test
	public void getRemarkList() {
		taskInfoService.getRemarkList("", "");
	}

	@Test
	public void listMyTestDoingTasks() {
		taskInfoService.listMyTestDoingTasks("", "", 0, 0);
	}
}
