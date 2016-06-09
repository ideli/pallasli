package com.pallasli.study.bpm.service.impl;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pallasli.study.bpm.model.Instance;
import com.pallasli.study.bpm.service.AuditService;
import com.pallasli.study.bpm.utils.InstanceUtils;

@Transactional
@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected FormService formService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected ManagementService managementService;
	@Autowired
	protected IdentityService identityService;

	@Override
	public Instance startProcessInstance(String user, String processKey) {
		ProcessInstance proInstance = runtimeService
				.startProcessInstanceByKey(processKey);
		Instance instance = InstanceUtils.convertActInstToMyInst(proInstance);
		return instance;
	}

	@Override
	public void completeTask(String user, String taskId) {
		taskService.complete(taskId);
	}

	@Override
	public void completeHqTask(String user, String taskId) {
		taskService.complete(taskId);

	}

	@Override
	public void delegateTask(String user, String taskId) {
		taskService.delegateTask(taskId, user);

	}

	@Override
	public void rebackDelegateTask(String user, String taskId) {
		taskService.delegateTask(taskId, user);

	}
}
