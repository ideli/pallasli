package com.pallasli.bpm.service.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.ProcessModelHandlerService;

public class DefaultProcessModelHandlerServiceImpl implements ProcessModelHandlerService {

	@Autowired
	RepositoryService repositoryService;

	@Override
	public void addActivityCandidate(String proDefId, String activityId, int candidateType) {
	}

	@Override
	public Deployment deploy(String xmlData) {
		DeploymentBuilder db = repositoryService.createDeployment();
		db.addString("lyttest.bpmn", new String(xmlData));
		Deployment deployment = db.deploy();
		return deployment;
	}

}
