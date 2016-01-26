package com.pallasli.bpm.api.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ProcessDefinitionService {
	/**
	 * 创建流程设计图
	 * 
	 * @param key
	 *            流程定义关键字
	 * @param name
	 *            流程名称
	 * @param category
	 * @param description
	 *            流程描述
	 * @return 流程定义唯一标示
	 */
	@WebMethod
	public String createProcessDefinition(@WebParam(name = "key") String key,
			@WebParam(name = "name") String name,
			@WebParam(name = "category") String category,
			@WebParam(name = "description") String description);

	/**
	 * 
	 * @param modelId
	 * @param key
	 * @param name
	 * @param category
	 * @param description
	 * @param editorNode
	 * @return
	 */
	@WebMethod
	public String saveProcessDefinition(
			@WebParam(name = "modelId") String modelId,
			@WebParam(name = "key") String key,
			@WebParam(name = "name") String name,
			@WebParam(name = "category") String category,
			@WebParam(name = "description") String description,
			@WebParam(name = "editorNode") Object editorNode);
}
