package com.pallasli.bpm.service.query;

import java.util.Map;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.pallasli.bpm.entity.InstanceInfo;

@WebService
public interface InstanceService {
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
	public InstanceInfo startProcessInstance(@WebParam(name = "user") String user,
			@WebParam(name = "processDefinitionKey") String processDefinitionKey,
			@WebParam(name = "businessKey") String businessKey,
			@WebParam(name = "variables") Map<String, Object> variables);

	/**
	 * 暂停流程
	 * 
	 * @param user
	 * @param processDefinitionKey
	 * @param businessKey
	 * @param variables
	 * @return
	 */
	@WebMethod
	public InstanceInfo suspendProcessInstance(@WebParam(name = "user") String user,
			@WebParam(name = "processDefinitionKey") String processDefinitionKey,
			@WebParam(name = "businessKey") String businessKey,
			@WebParam(name = "variables") Map<String, Object> variables);

	/**
	 * 激活流程
	 * 
	 * @param user
	 * @param processDefinitionKey
	 * @param businessKey
	 * @param variables
	 * @return
	 */
	@WebMethod
	public InstanceInfo activeProcessInstance(@WebParam(name = "user") String user,
			@WebParam(name = "processDefinitionKey") String processDefinitionKey,
			@WebParam(name = "businessKey") String businessKey,
			@WebParam(name = "variables") Map<String, Object> variables);

	/**
	 * 取消流程
	 * 
	 * @param user
	 * @param processDefinitionKey
	 * @param businessKey
	 * @param variables
	 * @return
	 */
	@WebMethod
	public InstanceInfo cancelProcessInstance(@WebParam(name = "user") String user,
			@WebParam(name = "processDefinitionKey") String processDefinitionKey,
			@WebParam(name = "businessKey") String businessKey,
			@WebParam(name = "variables") Map<String, Object> variables);

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
	public InstanceInfo openProcessInstance(@WebParam(name = "user") String user,
			@WebParam(name = "processKey") String processKey, @WebParam(name = "instanceId") String instanceId);
}
