package com.pallasli.bpm.api.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.pallasli.bpm.api.bean.InstanceInfo;
import com.pallasli.bpm.api.bean.TaskInfo;

@WebService
public interface TaskService {

	/**
	 * 查询待办任务信息
	 * 
	 * @param user
	 *            用户名
	 * @param state
	 *            状态([class]TaskState)
	 * @param keyword
	 *            查询主键字
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param firstNumber
	 *            起始行数
	 * @param pageSize
	 *            每页长度
	 * @return
	 */
	@WebMethod
	public List<TaskInfo> findTaskInfos(@WebParam(name = "user") String user,
			@WebParam(name = "state") int state,
			@WebParam(name = "keyword") String keyword,
			@WebParam(name = "startTime") String startTime,
			@WebParam(name = "endTime") String endTime,
			@WebParam(name = "firstNumber") int firstNumber,
			@WebParam(name = "pageSize") int pageSize);

	/**
	 * 完成待办任务
	 * 
	 * @param user
	 *            用户名
	 * @param taskId
	 *            任务唯一标识
	 * @param variables
	 *            流程和业务变量
	 * @return
	 */
	@WebMethod
	public InstanceInfo completeTask(@WebParam(name = "user") String user,
			@WebParam(name = "taskId") String taskId,
			@WebParam(name = "variables") Map<String, Object> variables);
}
