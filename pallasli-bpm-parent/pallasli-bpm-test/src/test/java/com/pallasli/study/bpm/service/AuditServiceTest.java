package com.pallasli.study.bpm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class AuditServiceTest extends BaseTest {

	@Test
	public void completeTask() {
		auditService.completeTask("", "");
	}

	@Test
	public void getNextNodeList() {
	}

	@Test
	public void getBackNodeList() {
	}

	@Test
	public void getRemarkList() {
	}

	@Test
	public void getProcessDiagram() {
	}

	@Test
	public void completeHqTask() {
		auditService.completeHqTask("", "");
	}

	@Test
	public void delegateTask() {
		auditService.delegateTask("", "");
	}

	@Test
	public void rebackDelegateTask() {
		auditService.rebackDelegateTask("", "");
	}

}
