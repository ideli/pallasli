package com.pallasli.bpm.dao.impl;

import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.bpm.bean.InstanceInfo;

public class InstanceServiceDao {
	@Autowired
	private RuntimeService runtimeService;

	public InstanceInfo startProcessInstance(String user,
			String processDefinitionKey, String businessKey,
			Map<String, Object> variables) {
		ExecutionEntity execEntity = (ExecutionEntity) runtimeService
				.startProcessInstanceByKey(processDefinitionKey, businessKey,
						variables);
		return getInstaceInfoFrom(execEntity);
	}

	private InstanceInfo getInstaceInfoFrom(ExecutionEntity execEntity) {

		String activityId = execEntity.getActivityId();//
		String businessKey = execEntity.getBusinessKey();//
		String currentActivityId = execEntity.getCurrentActivityId();
		String currentActivityName = execEntity.getCurrentActivityName();
		String deleteReason = execEntity.getDeleteReason();
		String deploymentId = execEntity.getDeploymentId();
		String id = execEntity.getId();
		String name = execEntity.getName();
		String processBusinessKey = execEntity.getProcessBusinessKey();
		String processDefinitionId = execEntity.getProcessDefinitionId();
		String processDefinitionKey = execEntity.getProcessDefinitionKey();
		String processDefinitionName = execEntity.getProcessDefinitionName();
		String processInstanceId = execEntity.getProcessInstanceId();

		InstanceInfo info = new InstanceInfo();
		// info.setBusinessKey(businessKey);
		// info.setCurrentActivityId(currentActivityId);
		// info.setCurrentActivityName(currentActivityName);
		// info.setProcessBusinessKey(processBusinessKey);
		// info.setProcessDefinitionKey(processDefinitionKey);
		// info.setProcessDefinitionName(processDefinitionName);
		// info.setProcessInstanceId(processInstanceId);

		return info;
	}

	public InstanceInfo openProcessInstance(String user, String processKey,
			String processInstanceId) {
		// TODO Auto-generated method stub
		return null;
	}

}
