package com.pallasli.study.bpm.service.impl;

import java.util.Date;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pallasli.study.bpm.service.MoniterService;

@Transactional
@Service
public class MoniterServiceImpl implements MoniterService {

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
	public void getProcessDefinitionList(String appKey, String processKey,
			String state, String keyword, int start, int pageNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean activateOrSuspendProcessDefintion(
			String processDefinitionId, boolean activate,
			boolean includeProcessInstances, Date startDate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getProcessInstanceList(String processDefinitionId,
			String keyword, int start, int pageNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean activateOrSuspendProcessInstance(String processInstanceId,
			boolean isActivate) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getProcessInstanceDetailInfo(String processInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getProcessInstanceActivityDetailInfo(String processInstanceId,
			int start, int pageNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getProcessInstanceTaskInfo(String processInstanceId, int start,
			int pageNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getProcessInstanceVariable(String processInstanceId, int start,
			int pageNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void findJobList(String processDefinitionId, int firstResult,
			int maxResults) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean executeOrDeleteJob(String jobId, boolean isDeleteJob) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void getProcessInstanceEventLog(String processInstanceId, int start,
			int pageNumber) {
		// TODO Auto-generated method stub

	}
}
