package com.pallasli.bpm.service.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.ProcessInstanceHandlerService;

public class DefaultProcessInstanceHandlerServiceImpl implements ProcessInstanceHandlerService {

	@Autowired
	RepositoryService repositoryService;
	@Autowired
	RuntimeService runtimeService;

	@Override
	public void cancel() {
	}

	@Override
	public void delete() {
	}

	@Override
	public void foreTerminate() {
	}

	@Override
	public ProcessInstance startByProKey(String proKey) {
		return runtimeService.startProcessInstanceByKey(proKey);

	}

}
