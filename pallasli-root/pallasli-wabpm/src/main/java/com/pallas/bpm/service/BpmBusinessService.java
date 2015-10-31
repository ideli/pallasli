package com.pallas.bpm.service;

import java.util.List;
import java.util.Map;

import com.pallas.bpm.service.bean.NodeInfo;
import com.pallas.bpm.service.bean.ProcessInstanceInfo;
import com.pallas.bpm.service.bean.RemarkInfo;
import com.pallas.bpm.service.bean.TaskInfo;

public interface BpmBusinessService {
	ProcessInstanceInfo startProcess(String userId, String processKey,
			String businessId, Map<String, Object> variables);

	List<TaskInfo> findTaskList(String userId, String processKey,
			String instanceId);

	boolean claimTask(String userId, String taskId);

	boolean completeTask(String userId, String taskId,
			Map<String, Object> variables);

	byte[] getDiagram(String processKey, String instanceId);

	List<RemarkInfo> getRemarkList(String processKey, String instanceId);

	NodeInfo getCurNodeInfo(String userId, String processKey, String instanceId);

	NodeInfo getPreNodeInfo(String userId, String processKey, String instanceId);

	List<NodeInfo> getNextNodeListInfo(String userId, String processKey,
			String instanceId);

	List<NodeInfo> getBackNodeListInfo(String userId, String processKey,
			String instanceId);

	NodeInfo getFirstNodeInfo(String userId, String processKey,
			String instanceId);

	void messageEventReceived(String msgName, String instanceId);
}
