package com.pallasli.study.bpm.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MoniterServiceTest extends BaseTest {

	@Test
	public void getProcessDefinitionList() {
		moniterService.getProcessDefinitionList("", "", "", "", 0, 0);
	}

	@Test
	public void activateOrSuspendProcessDefintion() {
		moniterService
				.activateOrSuspendProcessDefintion("", false, false, null);
	}

	@Test
	public void getProcessInstanceList() {
		moniterService.getProcessInstanceList("", "", 0, 0);
	}

	@Test
	public void activateOrSuspendProcessInstance() {
		moniterService.activateOrSuspendProcessInstance("", false);
	}

	@Test
	public void getProcessInstanceDetailInfo() {
		moniterService.getProcessInstanceDetailInfo("");
	}

	@Test
	public void getProcessInstanceActivityDetailInfo() {
		moniterService.getProcessInstanceActivityDetailInfo("", 0, 0);
	}

	@Test
	public void getProcessInstanceTaskInfo() {
		moniterService.getProcessInstanceTaskInfo("", 0, 0);
	}

	@Test
	public void findJobList() {
		moniterService.findJobList("", 0, 0);
	}

	@Test
	public void executeOrDeleteJob() {
		moniterService.executeOrDeleteJob("", false);
	}

	@Test
	public void getProcessInstanceEventLog() {
		moniterService.getProcessInstanceEventLog("", 0, 0);
	}

}
