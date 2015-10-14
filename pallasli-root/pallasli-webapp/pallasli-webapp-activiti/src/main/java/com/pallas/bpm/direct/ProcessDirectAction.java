package com.pallas.bpm.direct;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;

import com.pallas.bpm.direct.bean.BpmProcessInfo;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class ProcessDirectAction {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	public ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResourceDefault()
			.buildProcessEngine();
	public TaskService taskService = processEngine.getTaskService();
	public RepositoryService repoService = processEngine.getRepositoryService();
	public HistoryService historyService = processEngine.getHistoryService();

	/**
	 * 模型列表
	 */
	@DirectMethod(method = "processList")
	public List<BpmProcessInfo> processList(
			@RequestParam("userId") String userId) {
		List<BpmProcessInfo> list = new ArrayList<>();
		List<ProcessDefinition> deflist = repoService
				.createProcessDefinitionQuery().latestVersion()
				.orderByProcessDefinitionCategory().asc().list();
		BpmProcessInfo info = null;
		for (ProcessDefinition pd : deflist) {
			info = new BpmProcessInfo();
			info.setCategory(pd.getCategory());
			info.setDeploymentId(pd.getDeploymentId());
			info.setDescription(pd.getDescription());
			info.setDiagramResourceName(pd.getDiagramResourceName());
			info.setHasStartFormKey(pd.hasStartFormKey());
			info.setId(pd.getId());
			info.setKey(pd.getKey());
			info.setName(pd.getName());
			info.setResourceName(pd.getResourceName());
			info.setSuspended(pd.isSuspended());
			info.setTenantId(pd.getTenantId());
			info.setVersion(pd.getVersion());
			list.add(info);
		}
		return list;
	}

}
