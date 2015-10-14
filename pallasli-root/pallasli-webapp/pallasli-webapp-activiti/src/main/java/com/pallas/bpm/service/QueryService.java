package com.pallas.bpm.service;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import com.pallas.bpm.direct.bean.BpmTask;

public class QueryService {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResourceDefault()
			.buildProcessEngine();
	public TaskService taskService = processEngine.getTaskService();
	public HistoryService historyService = processEngine.getHistoryService();

	private HistoricTaskInstanceQuery dynatic(HistoricTaskInstanceQuery query,
			int processFinished) {
		if (processFinished == 0) {
			query.processUnfinished();
		} else if (processFinished == 1) {
			query.processFinished();
		}
		return null;
	}

	public List<BpmTask> taskDoneList(@RequestParam("userId") String userId) {
		HistoricTaskInstanceQuery query = historyService
				.createHistoricTaskInstanceQuery();
		dynatic(query, -1);
		List<HistoricTaskInstance> tasks = query.list();
		// .taskAssignee("USER(" + userId + ")").active().list();
		System.out.println(tasks.size());
		List<BpmTask> bpmTasks = new ArrayList<BpmTask>();
		for (HistoricTaskInstance t : tasks) {
			BpmTask bpmTask = new BpmTask();
			bpmTask.setAssignee(t.getAssignee());
			bpmTask.setCategory(t.getCategory());
			bpmTask.setCreateTime(t.getCreateTime());
			bpmTask.setDescription(t.getDescription());
			bpmTask.setDueDate(t.getDueDate());
			bpmTask.setExecutionId(t.getExecutionId());
			bpmTask.setFormKey(t.getFormKey());
			bpmTask.setId(t.getId());
			bpmTask.setName(t.getName());
			bpmTask.setOwner(t.getOwner());
			bpmTask.setParentTaskId(t.getParentTaskId());
			bpmTask.setPriority(t.getPriority());
			bpmTask.setProcessDefinitionId(t.getProcessDefinitionId());
			bpmTask.setProcessInstanceId(t.getProcessInstanceId());
			bpmTask.setTaskDefinitionKey(t.getTaskDefinitionKey());
			bpmTask.setTenantId(t.getTenantId());
			bpmTasks.add(bpmTask);
		}
		return bpmTasks;
	}
}
