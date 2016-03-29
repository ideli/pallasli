package com.pallasli.bpm.service.impl;

import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.service.ProcessModelQueryService;

public class DefaultProcessModelQueryServiceImpl implements ProcessModelQueryService {

	@Autowired
	RepositoryService repositoryService;

}
