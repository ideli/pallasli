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
import com.pallasli.study.bpm.service.InstanceService;
import com.pallasli.study.bpm.utils.InstanceUtils;

@Transactional
@Service
public class InstanceServiceImpl implements InstanceService {

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
	public Instance getProcessInstance(String user, String processKey,
			String processInstanceId) {
		ProcessInstance proInstance = runtimeService
				.createProcessInstanceQuery().processDefinitionKey(processKey)
				.processInstanceId(processInstanceId).singleResult();
		Instance instance = InstanceUtils.convertActInstToMyInst(proInstance);
		return instance;
	}

	@Override
	public void getProcessDiagram(String user, String processKey,
			String processInstanceId) {

	}

	@Override
	public void getHistoryProcessInstanceVar(String processInstanceId) {

	}
}
