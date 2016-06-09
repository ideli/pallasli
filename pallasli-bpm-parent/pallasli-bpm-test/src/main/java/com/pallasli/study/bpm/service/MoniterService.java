package com.pallasli.study.bpm.service;

import java.util.Date;

public interface MoniterService {

	public void getProcessDefinitionList(String appKey, String processKey,
			String state, String keyword, int start, int pageNumber);

	public boolean activateOrSuspendProcessDefintion(
			String processDefinitionId, boolean activate,
			boolean includeProcessInstances, Date startDate);

	public void getProcessInstanceList(String processDefinitionId,
			String keyword, int start, int pageNumber);

	public boolean activateOrSuspendProcessInstance(String processInstanceId,
			boolean isActivate);

	public void getProcessInstanceDetailInfo(String processInstanceId);

	public void getProcessInstanceActivityDetailInfo(String processInstanceId,
			int start, int pageNumber);

	public void getProcessInstanceTaskInfo(String processInstanceId, int start,
			int pageNumber);

	public void getProcessInstanceVariable(String processInstanceId, int start,
			int pageNumber);

	public void findJobList(String processDefinitionId, int firstResult,
			int maxResults);

	public boolean executeOrDeleteJob(String jobId, boolean isDeleteJob);

	public void getProcessInstanceEventLog(String processInstanceId, int start,
			int pageNumber);

}
