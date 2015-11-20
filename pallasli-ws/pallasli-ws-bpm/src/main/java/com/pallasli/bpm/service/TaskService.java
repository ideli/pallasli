package com.pallasli.bpm.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.pallasli.bpm.bean.InstanceInfo;
import com.pallasli.bpm.bean.TaskInfo;

public class TaskService {

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
			@WebParam(name = "pageSize") int pageSize) {

		Date s = null;
		Date e = null;

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		if (!StringUtils.isBlank(startTime)) {
			try {
				s = df.parse(startTime);
			} catch (ParseException e1) {
				log.warn("时间格式不正确", e1);
			}
		}

		if (!StringUtils.isBlank(endTime)) {
			try {
				s = df.parse(endTime);
			} catch (ParseException e1) {
				log.warn("时间格式不正确", e1);
			}
		}

		return taskServiceDao.findTaskInfos(user, state, keyword, s, e,
				firstNumber, pageSize);
	}

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
			@WebParam(name = "variables") Map<String, Object> variables) {
		return taskServiceDao.completeTask(user, taskId, variables);
	}
}
