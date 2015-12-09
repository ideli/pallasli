package org.activiti.designer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pallas.bpm.service.BpmBusinessService;
import com.pallas.bpm.service.BpmInstanceManagerService;
import com.pallas.bpm.service.BpmModelManagerService;
import com.pallas.bpm.service.BpmUserManagerService;
import com.pallas.bpm.service.bean.ProcessInstanceInfo;
import com.pallas.bpm.service.bean.TaskInfo;
import com.pallas.bpm.service.bean.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:activiti.cfg.xml")
public class ProcessTestMyProcess {

	Logger log = LoggerFactory.getLogger(this.getClass());

	private String filename = "/Users/lyt1987/Desktop/study/svn-checkout/workshop/wa-bpmservice/src/test/resources/TestProcess1.bpmn";
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private BpmInstanceManagerService instanceManagerService;
	@Autowired
	private BpmModelManagerService modelManagerService;
	@Autowired
	private BpmBusinessService businessService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private BpmUserManagerService userManagerService;

	@Before
	public void beforeFn() {
		log.info("------------>before");
	}

	@After
	public void afterFn() {
		log.info("------------>after");

	}

	@Test
	public void startProcess() throws Exception {

		repositoryService
				.createDeployment()
				.addInputStream("myProcess.bpmn20.xml",
						new FileInputStream(filename)).deploy();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("name", "Activiti");

		// modelManagerService.deleteModel(modelId);
		ProcessInstanceInfo processInstanceInfo = businessService.startProcess(
				"me", "myProcess", "1", variableMap);

		assertNotNull(processInstanceInfo.getId());

		businessService.messageEventReceived("startMsg",
				processInstanceInfo.getId());
		log.info(
				"{}{}",
				taskService.createTaskQuery()
						.processInstanceId(processInstanceInfo.getId()).list()
						.get(0).getName(), "test");

		List<UserInfo> list = userManagerService.findUserList("", 10, 1);
		for (UserInfo u : list) {
			log.info(u.getName());
		}
		List<TaskInfo> taskList = businessService.findTaskList(null,
				"myProcess", processInstanceInfo.getId());
		assertEquals(1, taskList.size());
		for (TaskInfo t : taskList) {
			businessService.claimTask("me", t.getId());
		}
		taskList = businessService.findTaskList("me", "myProcess",
				processInstanceInfo.getId());
		assertEquals(1, taskList.size());
		for (TaskInfo t : taskList) {
			businessService.completeTask("me", t.getId(), null);
		}

		taskList = businessService.findTaskList("me", "myProcess",
				processInstanceInfo.getId());
		assertEquals(0, taskList.size());

	}
}