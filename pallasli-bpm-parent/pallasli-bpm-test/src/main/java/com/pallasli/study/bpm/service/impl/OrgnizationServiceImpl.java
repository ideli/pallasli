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

import com.pallasli.study.bpm.service.OrgnizationService;

@Transactional
@Service
public class OrgnizationServiceImpl implements OrgnizationService {

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
	public void getUserInfoList(String keyword, int firstNumber, int pageSize) {

		historyService.createHistoricActivityInstanceQuery();
	}

	@Override
	public void getGroupInfoList(String keyword, int firstNumber, int pageSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getDepartmentInfoList(String keyword, int firstNumber,
			int pageSize) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getPositionInfoList(String keyword, int firstNumber,
			int pageSize) {
		// TODO Auto-generated method stub

	}

}
