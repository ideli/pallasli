package com.pallas.bpm.api;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pallas.bpm.service.BusinessService;
import com.pallas.bpm.service.bean.NodeInfo;
import com.pallas.bpm.service.bean.ProcessInstanceInfo;
import com.pallas.bpm.service.bean.TaskInfo;

/**
 * 
 * 业务流程处理测试类：启动流程，完成任务，获取下一节点，获取当前节点，获取上一节点，
 * 
 * 
 * @author lyt1987
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "" })
public class TestBusinessService {
	@Autowired
	BusinessService businessService;

	@Test
	public void startProcess() {

		String user = "";
		String password = "";
		String processKey = "";
		String businessKey = "";
		Map<String, Object> variables = new HashMap<String, Object>();

		ProcessInstanceInfo proIns = businessService.startProcess(processKey,
				businessKey, user, password, variables);
		assertNotNull(proIns);
	}

	@Test
	public void completeTask() {

		String user = "";
		String password = "";
		String processKey = "";
		String instanceId = "";
		Map<String, Object> variables = new HashMap<String, Object>();

		List<TaskInfo> taskInfoList = businessService.completeTask(processKey,
				instanceId, user, password, variables);
		assertNotNull(taskInfoList);
	}

	@Test
	public void getCurrentTask() {

		String user = "";
		String password = "";
		String processKey = "";
		String instanceId = "";
		String taskId = "";
		Map<String, Object> variables = new HashMap<String, Object>();

		TaskInfo taskInfo = businessService.getCurrentTask(processKey,
				instanceId, taskId, user, password, variables);
		assertNotNull(taskInfo);
	}

	@Test
	public void getNextNode() {

		String user = "";
		String password = "";
		String processKey = "";
		String instanceId = "";
		String taskId = "";
		Map<String, Object> variables = new HashMap<String, Object>();

		List<NodeInfo> nodeInfoList = businessService.getNextNode(processKey,
				instanceId, taskId, user, password, variables);
		
		assertNotNull(nodeInfoList);
	}

	@Test
	public void getPreTask() {

		String user = "";
		String password = "";
		String processKey = "";
		String instanceId = "";
		String taskId = "";

		TaskInfo taskInfo = businessService.getPreTask(processKey,
				instanceId,taskId, user, password);
		assertNotNull(taskInfo);
	}
	
	@Test
	public void getAllPreTask() {

		String user = "";
		String password = "";
		String processKey = "";
		String instanceId = "";
		String taskId = "";

		List<TaskInfo> taskInfoList = businessService.getAllPreTask(processKey,
				instanceId,taskId, user, password);
		assertNotNull(taskInfoList);
	}
}
