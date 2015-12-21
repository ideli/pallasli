package com.pallas.bpm.api;

import java.util.List;
import java.util.Map;

import com.pallas.bpm.bean.NodeInfo;
import com.pallas.bpm.bean.ProcessInstanceInfo;
import com.pallas.bpm.bean.TaskInfo;
/**
 * 业务流程处理类
 * @author lyt1987
 *
 */
public interface BusinessService {

	/**
	 * 启动流程
	 * @param processKey
	 * @param businessKey
	 * @param user
	 * @param password
	 * @param variables
	 * @return
	 */
	ProcessInstanceInfo startProcess(String processKey, String businessKey,
			String user, String password, Map<String, Object> variables);

	/**
	 * 获取上一环节办理的任务
	 * @param processKey
	 * @param instanceId
	 * @param taskId
	 * @param user
	 * @param password
	 * @return
	 */
	TaskInfo getPreTask(String processKey, String instanceId, String taskId,
			String user, String password);

	/**
	 * 获取下一环节
	 * @param processKey
	 * @param instanceId
	 * @param taskId
	 * @param user
	 * @param password
	 * @param variables
	 * @return
	 */
	List<NodeInfo> getNextNode(String processKey, String instanceId, String taskId,
			String user, String password, Map<String, Object> variables);

	/**
	 * 获取当前任务
	 * @param processKey
	 * @param instanceId
	 * @param taskId
	 * @param user
	 * @param password
	 * @param variables
	 * @return
	 */
	TaskInfo getCurrentTask(String processKey, String instanceId,
			String taskId, String user, String password,
			Map<String, Object> variables);

	/**
	 * 办理任务（普通办理，回退，会签，转交等）
	 * @param processKey
	 * @param instanceId
	 * @param user
	 * @param password
	 * @param variables
	 * @return
	 */
	List<TaskInfo> completeTask(String processKey, String instanceId,
			String user, String password, Map<String, Object> variables);

	/**
	 * 获取之前环节办理过的所有任务
	 * @param processKey
	 * @param instanceId
	 * @param taskId
	 * @param user
	 * @param password
	 * @return
	 */
	List<TaskInfo> getAllPreTask(String processKey, String instanceId,
			String taskId, String user, String password);

}
