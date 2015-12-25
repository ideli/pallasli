package com.pallasli.bpm.dao;

import java.util.Map;

import com.pallasli.bpm.bean.ExecutionInfo;
import com.pallasli.bpm.bean.InstanceInfo;

public interface InstanceServiceDao {

	public InstanceInfo startProcessInstance(String user,
			String processDefinitionKey, String businessKey,
			Map<String, Object> variables);

	public InstanceInfo getInstaceInfoFrom(ExecutionInfo execEntity);

	public InstanceInfo openProcessInstance(String user, String processKey,
			String processInstanceId);
}
