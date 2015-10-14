package com.pallas.manager.activiti;

import static org.junit.Assert.assertEquals;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;

public class MyBusinessProcessTest {
	@Rule
	public ActivitiRule activitiRule = new ActivitiRule(
			"activiti/activiti-context.cfg.xml");

	@Test
	@Deployment
	public void ruleUsageExample() {
		// activitiRule.setConfigurationResource("activiti/activiti.cfg.xml");
		// activitiRule.
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		runtimeService.startProcessInstanceByKey("vacationRequest");
		TaskService taskService = activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("My Task", task.getName());
		taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	}
}
