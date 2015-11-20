package com.pallasli.bpm.service;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.pallasli.bpm.bean.InstanceInfo;

public class InstanceService {
	/**
	 * 发起流程，得到流程信息
	 * 
	 * @param user
	 *            用户名
	 * @param processKey
	 *            流程定义唯一标识
	 * @param businessKey
	 *            业务主键
	 * @param variables
	 *            流程和业务变量
	 * @return
	 */
	@WebMethod
	public InstanceInfo startProcessInstance(
			@WebParam(name = "user") String user,
			@WebParam(name = "processDefinitionKey") String processDefinitionKey,
			@WebParam(name = "businessKey") String businessKey,
			@WebParam(name = "variables") Map<String, Object> variables) {
		try {

			return instanceServiceDao.startProcessInstance(user,
					processDefinitionKey, businessKey, variables);

		} catch (Exception e) {
			log.error("start process error:{}", e);
			throw new PallasActivitiExecption("发起流程失败");
		}

	}

	/**
	 * 查询流程实例信息
	 * 
	 * @param user
	 *            用户名
	 * @param processKey
	 *            流程定义唯一标识
	 * @param instanceId
	 *            流程实例唯一标识
	 * @return
	 */
	@WebMethod
	public InstanceInfo openProcessInstance(
			@WebParam(name = "user") String user,
			@WebParam(name = "processKey") String processKey,
			@WebParam(name = "instanceId") String instanceId) {
		return instanceServiceDao.openProcessInstance(user, processKey,
				instanceId);
	}
}
