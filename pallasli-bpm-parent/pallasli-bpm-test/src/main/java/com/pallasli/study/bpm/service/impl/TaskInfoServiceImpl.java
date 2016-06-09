package com.pallasli.study.bpm.service.impl;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pallasli.study.bpm.service.TaskInfoService;

@Transactional
@Service
public class TaskInfoServiceImpl implements TaskInfoService {

	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected TaskInfoService taskService;
	@Autowired
	protected FormService formService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected ManagementService managementService;
	@Autowired
	protected IdentityService identityService;

	@Override
	public void listTaskInfos() {
		// TODO Auto-generated method stub

	}

	@Override
	public void listDuaTimeoutTask(String user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getRemarkList(String user, String processInstanceId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void listMyTestDoingTasks(String user, String state,
			int firstNumber, int pageSize) {
		// TODO Auto-generated method stub

	}
}
