package com.pallasli.study.bpm.service;

public interface TaskInfoService {

	/**
	 * 查询待办任务信息
	 * 
	 * @param channels
	 *            办理渠道
	 * @param user
	 *            用户
	 * @param state
	 *            状态 待办0 待阅1 已办2 已阅 3 申请4 待审批中5 委托6 草稿7 已办结8
	 * @param keyword
	 *            查询主键字
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return
	 */
	public void listTaskInfos();

	/**
	 * 延期任务列表
	 * 
	 * @param user
	 */
	public void listDuaTimeoutTask(String user);

	/**
	 * 获取流程流转记录列表
	 * 
	 * @param user
	 *            用户
	 * @param processInstanceId
	 *            流程实例ID
	 * @return
	 */
	public void getRemarkList(String user, String processInstanceId);

	/**
	 * 测试数据-任务列表
	 * 
	 * @param user
	 * @param state
	 * @param firstNumber
	 * @param pageSize
	 */
	public void listMyTestDoingTasks(String user, String state,
			int firstNumber, int pageSize);
}
