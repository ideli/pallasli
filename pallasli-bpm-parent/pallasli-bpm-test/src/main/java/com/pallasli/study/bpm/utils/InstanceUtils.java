package com.pallasli.study.bpm.utils;

import org.activiti.engine.runtime.ProcessInstance;

import com.pallasli.study.bpm.model.Instance;

/**
 * 
 * @author lyt1987
 *
 */
public class InstanceUtils {
	public static Instance convertActInstToMyInst(ProcessInstance proInstance) {
		if (proInstance == null) {
			return null;
		}
		Instance instance = new Instance();
		instance.setSuspended(proInstance.isSuspended());
		instance.setBusinessKey(proInstance.getBusinessKey());
		instance.setId(proInstance.getId());
		instance.setInstanceId(proInstance.getProcessInstanceId());
		instance.setName(proInstance.getName());
		instance.setParentId(proInstance.getParentId());
		instance.setProcessDefId(proInstance.getProcessDefinitionId());
		instance.setProcessKey(proInstance.getProcessDefinitionKey());

		return instance;
	}
}
