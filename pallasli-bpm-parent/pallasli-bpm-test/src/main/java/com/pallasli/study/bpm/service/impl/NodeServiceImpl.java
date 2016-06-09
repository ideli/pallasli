package com.pallasli.study.bpm.service.impl;

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

import com.pallasli.study.bpm.service.NodeService;

@Transactional
@Service
public class NodeServiceImpl implements NodeService {

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
	public void getNextNodeList() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getBackNodeList() {
		// TODO Auto-generated method stub

	}
}
