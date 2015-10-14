package com.pallas.bpm.direct;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import com.pallas.bpm.direct.bean.BpmTask;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class TaskDirectAction {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResourceDefault()
			.buildProcessEngine();
	public TaskService taskService = processEngine.getTaskService();
	public HistoryService historyService = processEngine.getHistoryService();

	/**
	 * 模型列表
	 */
	@DirectMethod(method = "taskDoingList")
	public List<BpmTask> taskDoingList(@RequestParam("userId") String userId) {
		System.out.println(userId);
		List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId)
				.active().listPage(0, 10);
		System.out.println(tasks.size());
		List<BpmTask> bpmTasks = new ArrayList<BpmTask>();
		for (Task t : tasks) {
			BpmTask bpmTask = new BpmTask();
			bpmTask.setAssignee(t.getAssignee());
			bpmTask.setCategory(t.getCategory());
			bpmTask.setCreateTime(t.getCreateTime());
			bpmTask.setDelegationState(t.getDelegationState() == null ? "" : t
					.getDelegationState().name());
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

	private void dynatic(HistoricTaskInstanceQuery query, int processFinished) {
		if (processFinished == 0) {
			query.processUnfinished();
		} else if (processFinished == 1) {
			query.processFinished();
		}
	}

	/**
	 * 模型列表
	 */
	@DirectMethod(method = "taskDoneList")
	public List<BpmTask> taskDoneList(@RequestParam("userId") String userId) {
		HistoricTaskInstanceQuery query = historyService
				.createHistoricTaskInstanceQuery();
		dynatic(query, -1);
		List<HistoricTaskInstance> tasks = query.listPage(0, 30);
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
