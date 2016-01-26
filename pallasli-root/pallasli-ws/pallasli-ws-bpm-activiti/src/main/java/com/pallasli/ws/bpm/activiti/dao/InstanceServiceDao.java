package com.pallasli.ws.bpm.activiti.dao;

import java.util.Map;

import com.pallasli.bpm.api.bean.ExecutionInfo;
import com.pallasli.bpm.api.bean.InstanceInfo;

public interface InstanceServiceDao {

	public InstanceInfo startProcessInstance(String user,
			String processDefinitionKey, String businessKey,
			Map<String, Object> variables);

	public InstanceInfo getInstaceInfoFrom(ExecutionInfo execEntity);

	public InstanceInfo openProcessInstance(String user, String processKey,
			String processInstanceId);
}
