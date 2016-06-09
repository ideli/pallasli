package com.pallasli.study.bpm.model;

public class Instance {
	// 流程执行实例id
	private String id;
	// 流程实例id
	private String instanceId;
	// 流程实例名称
	private String name;
	// 父流程实例id
	private String parentId;
	// 流程key
	private String processKey;
	// 流程定义id（含版本号）
	private String processDefId;
	// 业务主键key
	private String businessKey;
	// 流程实例状态（是否挂起）
	private boolean suspended;

	//
	//
	//
	//
	//
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getProcessKey() {
		return processKey;
	}

	public void setProcessKey(String processKey) {
		this.processKey = processKey;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getBusinessKey() {
		return businessKey;
	}

	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

}
