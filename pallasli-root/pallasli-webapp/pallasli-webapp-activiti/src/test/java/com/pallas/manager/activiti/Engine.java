package com.pallas.manager.activiti;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.VariableInstanceEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Engine {
	Logger logger = LoggerFactory.getLogger(Engine.class);

	@Test
	public void testEngine() {
		// ProcessEngine processEngine =
		// ProcessEngines.getDefaultProcessEngine();

		ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti/activiti.cfg.xml");
		// ProcessEngine processEngine = ProcessEngineConfiguration
		// .createStandaloneInMemProcessEngineConfiguration()
		// .setDatabaseSchemaUpdate(
		// ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE)
		// .setJdbcUrl("jdbc:h2:mem:my-own-db;DB_CLOSE_DELAY=1000")
		// .setJobExecutorActivate(true).buildProcessEngine();
		ProcessEngine processEngine = ProcessEngineConfiguration
				.createStandaloneInMemProcessEngineConfiguration()
				.setDatabaseSchemaUpdate("create").buildProcessEngine();
		Assert.assertEquals("default", processEngine.getName());

		RuntimeService runtimeService = processEngine.getRuntimeService();

		RepositoryService repositoryService = processEngine
				.getRepositoryService();
		TaskService taskService = processEngine.getTaskService();
		ManagementService managementService = processEngine
				.getManagementService();
		IdentityService identityService = processEngine.getIdentityService();
		HistoryService historyService = processEngine.getHistoryService();
		FormService formService = processEngine.getFormService();

		// 发布流程
		repositoryService
				.createDeployment()
				.addClasspathResource(
						"activiti/bpmn20/VacationRequest.bpmn20.xml").deploy();
		logger.info("Number of process definitions: "
				+ repositoryService.createProcessDefinitionQuery().count());

		// 启动流程实例
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey("vacationRequest", variables);
		// Verify that we started a new process instance
		logger.info("Number of process instances: "
				+ runtimeService.createProcessInstanceQuery().count());

		// 完成任务
		// Fetch all tasks for the management group
		List<Task> tasks = taskService.createTaskQuery()
				.taskCandidateGroup("management").list();
		for (Task task : tasks) {
			logger.info("Task available: " + task.getName());
		}

		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");

		taskService.complete(task.getId(), taskVariables);
		logger.info("完成任务");
		// 挂起流程
		repositoryService.suspendProcessDefinitionByKey("vacationRequest");
		logger.info("挂起流程");
		try {
			runtimeService.startProcessInstanceByKey("vacationRequest");
		} catch (ActivitiException e) {
			logger.error("流程已挂起");
		}
		// 激活流程
		repositoryService.activateProcessDefinitionByKey("vacationRequest");
		logger.info("激活流程");
		try {
			runtimeService.startProcessInstanceByKey("vacationRequest",
					variables);
		} catch (ActivitiException e) {
			e.printStackTrace();
		}

		// 原生查询
		long count = taskService
				.createNativeTaskQuery()
				.sql("SELECT count(*) FROM "
						+ managementService.getTableName(Task.class)
						+ " T1, "
						+ managementService
								.getTableName(VariableInstanceEntity.class)
						+ " V1 WHERE V1.TASK_ID_ = T1.ID_").count();
		count = taskService
				.createNativeTaskQuery()
				.sql("SELECT count(*) FROM "
						+ managementService
								.getTableName(VariableInstanceEntity.class)
						+ " V1  ").count();
		logger.info("原生查询" + count);
	}
}
