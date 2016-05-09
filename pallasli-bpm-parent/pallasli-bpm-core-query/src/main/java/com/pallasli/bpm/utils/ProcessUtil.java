package com.pallasli.bpm.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.engine.EngineServices;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.VariableScope;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.el.ExpressionManager;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.apache.commons.lang3.StringUtils;

public final class ProcessUtil {
	private ProcessUtil() {
	}

	public static ProcessInstance getProInst(RuntimeService runtimeService, String proInstId) {
		ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(proInstId)
				.singleResult();
		return instance;
	}

	public static String getBusinessKeyByProInst(RuntimeService runtimeService, String proInstId) {
		return getProInst(runtimeService, proInstId).getBusinessKey();
	}

	public static ProcessDefinitionEntity getProDef(RepositoryService repositoryService, String proDefId) {
		ProcessDefinitionEntity processDefEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition(proDefId);
		return processDefEntity;
	}

	public static List<ActivityImpl> getActsByProcDef(RepositoryService repoService, String proDefId) {
		ProcessDefinitionEntity processDefEntity = getProDef(repoService, proDefId);
		if (processDefEntity == null)
			return new ArrayList<ActivityImpl>();
		List<ActivityImpl> activityImpls = processDefEntity.getActivities();
		return activityImpls;
	}

	public static String getProDefIdByProKey(RepositoryService repoService, String processKey) {
		ProcessDefinition processDef = repoService.createProcessDefinitionQuery().processDefinitionKey(processKey)
				.latestVersion().singleResult();
		String processDefId = processDef.getId();
		return processDefId;
	}

	public static ProcessDefinitionEntity getProDefByKey(RepositoryService repoService, String proKey) {
		String processDefId = getProDefIdByProKey(repoService, proKey);
		return getProDef(repoService, processDefId);
	}

	public static String getProKeyByProDefId(RepositoryService repoService, String proDefId) {
		ProcessDefinitionEntity procDefEntity = getProDef(repoService, proDefId);
		return getProcessKey(procDefEntity);
	}

	public static String getProcessAppKey(RepositoryService repoService, String processDefId) {
		ProcessDefinitionEntity procDefEntity = getProDef(repoService, processDefId);
		return getProcessAppKey(procDefEntity);
	}

	public static String getProcessKey(ProcessDefinitionEntity processDefEntity) {
		return processDefEntity.getKey();
	}

	public static String getProcessAppKey(ProcessDefinitionEntity processDefEntity) {
		String appKey = processDefEntity.getCategory();
		return appKey;
	}

	public static HistoricProcessInstance getHisProInst(HistoryService historyService, String proInstId) {
		HistoricProcessInstance hisProInst = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(proInstId).includeProcessVariables().singleResult();
		return hisProInst;
	}

	public static HistoricProcessInstance getHisProInst(HistoryService historyService, String proInstId,
			Map<String, Object> variable) {
		HistoricProcessInstanceQuery hisProInstQuery = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(proInstId);

		HistoricProcessInstance hisProInst = hisProInstQuery.includeProcessVariables().singleResult();
		return hisProInst;
	}

	public static Map<String, Object> getProcessVariable(HistoricProcessInstance hisProInst) {
		Map<String, Object> allProcessVariables = hisProInst.getProcessVariables();
		return allProcessVariables;
	}

	public static String getProInstSubmiterId(HistoryService historyService, String proInstId) {
		HistoricProcessInstance historicProcessInstance = getHisProInst(historyService, proInstId);
		if (historicProcessInstance == null) {
			return StringUtils.EMPTY;
		}
		return getProInstSubmiterId((HistoricProcessInstanceEntity) historicProcessInstance);
	}

	public static String getProInstSubmiterId(HistoricProcessInstanceEntity historicProcessInstance) {
		String submiter = historicProcessInstance.getStartUserId();
		return submiter;
	}

	public static String getProInstSubmiterName(HistoricProcessInstanceEntity historicProcessInstance,
			IdentityService identityService) {
		String submiter = getProInstSubmiterId(historicProcessInstance);
		if (StringUtils.isNotBlank(submiter)) {
			// return OrganizationUtil.getUserName(identityService, submiter);
		}
		return StringUtils.EMPTY;
	}

	public static String getProInstSubmiterName(HistoryService historyService, String proInstId,
			IdentityService identityService) {
		String submiter = getProInstSubmiterId(historyService, proInstId);
		if (StringUtils.isNotBlank(submiter)) {
			// return OrganizationUtil.getUserName(identityService, submiter);
		}
		return StringUtils.EMPTY;
	}

	public static Object calculateProExpressionValue(EngineServices engineService, String expression,
			VariableScope variable) {
		SpringProcessEngineConfiguration config = (SpringProcessEngineConfiguration) engineService
				.getProcessEngineConfiguration();
		ExpressionManager expressManager = config.getExpressionManager();
		Expression exp = expressManager.createExpression(expression);
		Object result = exp.getValue(variable);
		return result;
	}

}
