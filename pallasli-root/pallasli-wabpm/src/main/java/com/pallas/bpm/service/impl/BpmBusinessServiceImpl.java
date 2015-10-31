package com.pallas.bpm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallas.bpm.service.BpmBusinessService;
import com.pallas.bpm.service.BpmInstanceManagerService;
import com.pallas.bpm.service.BpmModelManagerService;
import com.pallas.bpm.service.bean.NodeInfo;
import com.pallas.bpm.service.bean.ProcessInstanceInfo;
import com.pallas.bpm.service.bean.RemarkInfo;
import com.pallas.bpm.service.bean.TaskInfo;

public class BpmBusinessServiceImpl implements BpmBusinessService {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private HistoryService historyService;

	@Override
	public ProcessInstanceInfo startProcess(String userId, String processKey,
			String businessId, Map<String, Object> variables) {
		ProcessInstance processInstance = runtimeService
				.startProcessInstanceByKey(processKey, variables);
		log.info("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
		log.info(taskService.createTaskQuery()
				.processInstanceId(processInstance.getId()).list().get(0)
				.getName());
		ProcessInstanceInfo instanceInfo = new ProcessInstanceInfo();
		instanceInfo.setId(processInstance.getId());
		return instanceInfo;
	}

	@Override
	public List<TaskInfo> findTaskList(String userId, String processKey,
			String instanceId) {
		TaskQuery query = taskService.createTaskQuery();
		query.processDefinitionKey(processKey).processInstanceId(instanceId);
		if (userId != null)
			query.taskCandidateOrAssigned(userId);
		List<Task> list = query.listPage(0, 10);
		List<TaskInfo> infoList = new ArrayList<TaskInfo>();
		for (Task t : list) {
			TaskInfo info = new TaskInfo();
			info.setId(t.getId());
			infoList.add(info);
		}

		return infoList;
	}

	@Override
	public boolean claimTask(String userId, String taskId) {
		taskService.claim(taskId, userId);

		return true;
	}

	@Override
	public boolean completeTask(String userId, String taskId,
			Map<String, Object> variables) {
		taskService.complete(taskId, variables, true);

		return true;
	}

	@Override
	public byte[] getDiagram(String processKey, String instanceId) {
		byte[] diagram = null;
		return diagram;
	}

	@Override
	public List<RemarkInfo> getRemarkList(String processKey, String instanceId) {
		List<RemarkInfo> infoList = new ArrayList<RemarkInfo>();
		historyService.createHistoricVariableInstanceQuery();

		return infoList;
	}

	@Override
	public NodeInfo getCurNodeInfo(String userId, String processKey,
			String instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeInfo getPreNodeInfo(String userId, String processKey,
			String instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeInfo> getNextNodeListInfo(String userId, String processKey,
			String instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeInfo> getBackNodeListInfo(String userId, String processKey,
			String instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeInfo getFirstNodeInfo(String userId, String processKey,
			String instanceId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void messageEventReceived(String msgName, String instanceId) {

		runtimeService.messageEventReceived(msgName, instanceId);
	}

}
