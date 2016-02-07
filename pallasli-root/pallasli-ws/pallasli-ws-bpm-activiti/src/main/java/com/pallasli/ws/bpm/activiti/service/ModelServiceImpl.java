package com.pallasli.ws.bpm.activiti.service;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.pallasli.bpm.api.bean.ModelInfo;
import com.pallasli.bpm.api.service.ModelService;

public class ModelServiceImpl implements ModelService {

	/**
	 * 用于流程设计器初始化
	 * 
	 * @param modelId
	 *            流程定义唯一标示
	 * @return
	 */
	@Override
	@WebMethod
	public String getEditorSource(@WebParam(name = "modelId") String modelId) {
		return null;
	}

	/**
	 * 查询流程设计信息
	 * 
	 * @param keyword
	 *            关键字
	 * @param firstResult
	 *            起始行数
	 * @param pageSize
	 *            每页长度
	 * @return
	 */
	@Override
	@WebMethod
	public List<ModelInfo> findProcessModelInfo(@WebParam(name = "keyword") String keyword,
			@WebParam(name = "firstNumber") int firstResult, @WebParam(name = "pageNumber") int pageSize) {

		return null;
	}

	/**
	 * 删除流程设计图
	 * 
	 * @param modelId
	 *            流程设计ID
	 * @return
	 */
	@Override
	@WebMethod
	public boolean deleteProcessModel(@WebParam(name = "modelId") String modelId) {
		return false;
	}

	/**
	 * 复制流程设计图
	 * 
	 * @param name
	 *            名称
	 * @param description
	 *            描述
	 * @param modelId
	 *            复制的流程设计ID
	 * @return
	 */
	@Override
	@WebMethod
	public String copyProcessModel(@WebParam(name = "name") String name,
			@WebParam(name = "description") String description, @WebParam(name = "modelId") String modelId) {
		return null;
	}

	/**
	 * 导出流程信息
	 * 
	 * @param modelId
	 *            流程设计ID
	 * @return
	 */
	@Override
	@WebMethod
	public ModelInfo exportProcessModelInfo(@WebParam(name = "modelId") String modelId) {
		return null;
	}

	/**
	 * 导入的流程信息
	 * 
	 * @param modelXMLInfo
	 * @Param isOverride 是否覆盖，true是覆盖
	 * @return
	 */
	@Override
	@WebMethod
	public boolean importProcessModelInfo(ModelInfo modelXMLInfo, boolean isOverride) {
		return false;
	}

	/**
	 * 部署流程
	 * 
	 * @param modelId
	 *            流程设计ID
	 * @return
	 */
	@Override
	@WebMethod
	public boolean deployProcessModel(@WebParam(name = "modelId") String modelId) {
		return false;
	}

	/**
	 * 取消部署流程
	 * 
	 * @param modelId
	 * @return
	 */
	@Override
	@WebMethod
	public boolean unDeployProcessModel(@WebParam(name = "modelId") String modelId) {
		return false;
	}

	@Override
	public boolean createProcessModel() {
		// TODO Auto-generated method stub
		return false;
	}
}
