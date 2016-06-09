package com.pallasli.study.bpm.service;

import com.pallasli.study.bpm.model.Instance;

public interface AuditService {

	/**
	 * 发起流程（第一个接口），用于提交数据到流程中，得到流程信息
	 * 
	 * @param user
	 *            用户编号
	 * @param processKey
	 *            流程KEY，在流程设计中定义
	 * @param businessKey
	 *            业务主键
	 * @param channel
	 *            办理渠道
	 * @param variables
	 *            流程和业务变量
	 * @return
	 */
	public Instance startProcessInstance(String user, String processKey);

	/**
	 * 完成待办任务
	 * 
	 * @param user
	 *            用户
	 * @param taskId
	 *            任务ID，从任务待办列表中获取
	 * @param variables
	 *            提交的流程变量和表单变量内容
	 * @param businessKey
	 *            业务主键
	 * @param channel
	 *            办理渠道
	 * @return
	 */
	public void completeTask(String user, String taskId);

	/**
	 * 完成待办任务（会签）
	 * 
	 * @param user
	 * @param taskId
	 * @param variables
	 * @param channel
	 * @return
	 */
	public void completeHqTask(String user, String taskId);

	/**
	 * 完成待办任务（代理）
	 */
	public void delegateTask(String user, String taskId);

	/**
	 * 取消代理任务
	 */
	public void rebackDelegateTask(String user, String taskId);

}
