package com.pallasli.bpm.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;

import com.pallasli.bpm.bean.ModelInfo;

public class ModelService {

	/**
	 * 用于流程设计器初始化
	 * 
	 * @param modelId
	 *            流程定义唯一标示
	 * @return
	 */
	@WebMethod
	public String getEditorSource(@WebParam(name = "modelId") String modelId) {
		byte[] bytes = repositoryServiceDao.getEditorSource(modelId);
		String ret = "{}";
		try {
			if (bytes != null) {
				ret = new String(bytes, "utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
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
	@WebMethod
	public List<ModelInfo> findProcessModelInfo(
			@WebParam(name = "keyword") String keyword,
			@WebParam(name = "firstNumber") int firstResult,
			@WebParam(name = "pageNumber") int pageSize) {

		return definitionServiceDao.findProcessModelInfo(keyword, firstResult,
				pageSize);
	}

	/**
	 * 删除流程设计图
	 * 
	 * @param modelId
	 *            流程设计ID
	 * @return
	 */
	@WebMethod
	public boolean deleteProcessModel(@WebParam(name = "modelId") String modelId) {
		return repositoryServiceDao.deleteProcessModel(modelId);
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
	@WebMethod
	public String copyProcessModel(@WebParam(name = "name") String name,
			@WebParam(name = "description") String description,
			@WebParam(name = "modelId") String modelId) {
		return repositoryServiceDao
				.copyProcessModel(name, description, modelId);
	}

	/**
	 * 导出流程信息
	 * 
	 * @param modelId
	 *            流程设计ID
	 * @return
	 */
	@WebMethod
	public ModelXMLInfo exportProcessModelInfo(
			@WebParam(name = "modelId") String modelId) {
		return repositoryServiceDao.exportProcessModelInfo(modelId);
	}

	/**
	 * 导入的流程信息
	 * 
	 * @param modelXMLInfo
	 * @Param isOverride 是否覆盖，true是覆盖
	 * @return
	 */
	@WebMethod
	public boolean importProcessModelInfo(ModelXMLInfo modelXMLInfo,
			boolean isOverride) {
		return repositoryServiceDao.importProcessModelInfo(modelXMLInfo,
				isOverride);
	}

	/**
	 * 部署流程
	 * 
	 * @param modelId
	 *            流程设计ID
	 * @return
	 */
	@WebMethod
	public boolean deployProcessModel(@WebParam(name = "modelId") String modelId) {
		return repositoryServiceDao.deleteProcessModel(modelId);
	}

	/**
	 * 取消部署流程
	 * 
	 * @param modelId
	 * @return
	 */
	@WebMethod
	public boolean unDeployProcessModel(
			@WebParam(name = "modelId") String modelId) {
		return repositoryServiceDao.unDeployProcessModel(modelId);
	}
}
