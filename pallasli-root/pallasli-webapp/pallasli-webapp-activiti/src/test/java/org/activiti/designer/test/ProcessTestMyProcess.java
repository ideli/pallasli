package org.activiti.designer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

@Ignore
public class ProcessTestMyProcess {

	private String filename = "I://workspace/activitiTest/src/test/resource/standalone/MyProcess.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule("/standalone/activiti.cfg.xml");

	@Test
	public void startProcess() throws Exception {
		// 发布
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		// repositoryService
		// .createDeployment()
		// .addInputStream("myProcess.bpmn20.xml",
		// new FileInputStream(filename)).deploy();

		repositoryService.createDeployment().addClasspathResource("standalone/MyProcess.bpmn").deploy();

		ProcessDefinition pd = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess")
				.singleResult();
		System.out.println(pd.getKey());
		System.out.println(pd.getDiagramResourceName());
		// 启动
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("applyUser", "activiti");
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess", variableMap);
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " " + processInstance.getProcessDefinitionId());

		// 查询
		TaskService taskService = activitiRule.getTaskService();
		Task curTask = taskService.createTaskQuery().taskAssignee("activiti").singleResult();
		assertNotNull(curTask);
		assertEquals("审核", curTask.getName());
		// 签收
		taskService.claim(curTask.getId(), "activiti");

		// 办理
		variableMap = new HashMap<String, Object>();
		variableMap.put("approved", true);
		taskService.complete(curTask.getId(), variableMap);

		curTask = taskService.createTaskQuery().taskAssignee("activiti").singleResult();
		assertNotNull(curTask);
		assertEquals("审批", curTask.getName());
		taskService.claim(curTask.getId(), "activiti");
		variableMap = new HashMap<String, Object>();
		variableMap.put("approved", true);
		taskService.complete(curTask.getId(), variableMap);

		curTask = taskService.createTaskQuery().taskAssignee("activiti").singleResult();
		assertNotNull(curTask);
		assertEquals("终审", curTask.getName());
		taskService.claim(curTask.getId(), "activiti");
		variableMap = new HashMap<String, Object>();
		variableMap.put("approved", "true");
		taskService.complete(curTask.getId(), variableMap);

		curTask = taskService.createTaskQuery().taskAssignee("activiti").singleResult();
		assertNull(curTask);

		// 历史查询
		HistoryService historyService = activitiRule.getHistoryService();

	}
}