package com.pallasli.study.bpm.service;

public interface NodeService {

	/**
	 * 查询当前可执行的下一环节列表
	 * 
	 * @param user
	 *            用户
	 * @param processKey
	 *            流程Key，流程设计图中定义
	 * @param processInstanceId
	 *            流程实例，从待办任务列表中获取或业务人员从业务处理接口中保存后得到
	 * @param currentActivityId
	 *            当前流程结点
	 * @param variables
	 *            流程变量
	 * @return
	 */
	public void getNextNodeList();

	/**
	 * 查询当前可回退的下一环节列表
	 * 
	 * @param user
	 *            用户
	 * @param processKey
	 *            流程Key，流程设计图中定义
	 * @param processInstanceId
	 *            流程实例，从待办任务列表中获取或业务人员从业务处理接口中保存后得到
	 * @param currentActivityId
	 *            当前流程结点
	 * @return
	 */
	public void getBackNodeList();
}
