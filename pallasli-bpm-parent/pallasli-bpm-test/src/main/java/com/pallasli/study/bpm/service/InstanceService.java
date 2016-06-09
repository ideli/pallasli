package com.pallasli.study.bpm.service;

import com.pallasli.study.bpm.model.Instance;

public interface InstanceService {
	/**
	 * 查询流程实例信息
	 * 
	 * @param user
	 *            用户
	 * @param processKey
	 *            流程Key，流程设计图中定义
	 * @param processInstanceId
	 *            流程实例，从待办任务列表中获取或业务人员从业务处理接口中保存后得到
	 * @return
	 */
	public Instance getProcessInstance(String user, String processKey,
			String processInstanceId);

	/**
	 * 获取流程图
	 * 
	 * @param user
	 *            当前用户
	 * @param processKey
	 *            流程KEY
	 * @param processInstanceId
	 *            流程实例ID,为空时，查看流程设计图
	 * @return
	 */
	public void getProcessDiagram(String user, String processKey,
			String processInstanceId);

	/**
	 * 获取流程变量
	 * 
	 * @param processInstanceId
	 */
	public void getHistoryProcessInstanceVar(String processInstanceId);
}
