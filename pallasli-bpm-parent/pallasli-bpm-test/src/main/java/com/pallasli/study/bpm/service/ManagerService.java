package com.pallasli.study.bpm.service;

import java.io.InputStream;
import java.util.List;

import org.activiti.engine.repository.Model;

/**
 * 流程管理
 * 
 * @author lyt
 * 
 */
public interface ManagerService {

	/**
	 * 查看流程模型列表
	 * 
	 * @param appKey
	 * @param keyword
	 * @param firstResult
	 * @param pageSize
	 */
	public List<Model> listProcessModels(String appKey, String keyword,
			int firstResult, int pageSize);

	/**
	 * 创建流程模型
	 * 
	 * @param name
	 * @param description
	 * @param appKey
	 * @return
	 */
	public String createProcessModel(String name, String description,
			String appKey, String processkey);

	/**
	 * 保存流程模型
	 * 
	 * @param modelId
	 * @return
	 */
	public boolean saveProcessModel(String modelId, String processModelJson);

	/**
	 * 删除流程模型
	 * 
	 * @param modelId
	 * @return
	 */
	public boolean deleteProcessModel(String modelId);

	/**
	 * 复制流程模型
	 * 
	 * @param modelId
	 * @return
	 */
	public String copyProcessModel(String name, String description,
			String appKey, String modelId);

	/**
	 * 发布流程
	 * 
	 * @param modelId
	 * @return
	 */
	public boolean deployProcessModel(String modelId);

	/**
	 * 取消发布流程
	 * 
	 * @param modelId
	 * @param isForceDeleteAllHistory
	 * @param deploymentId
	 * @return
	 */
	public boolean unDeployProcessModel(String modelId,
			boolean isForceDeleteAllHistory, String deploymentId);

	/**
	 * 导出未发布的流程（json）
	 * 
	 * @param modelId
	 * @return
	 */
	public String exportProcessModelInfo(String modelId);

	/**
	 * 导出已发布的流程（xml）
	 * 
	 * @param deploymentId
	 * @return
	 */
	public InputStream exportProcessModelInfoByDeploymentId(String deploymentId);

	/**
	 * 导入流程
	 * 
	 * @return
	 */
	public boolean importProcessModelInfo(String processModelJson);

	/**
	 * 获取流程定义
	 * 
	 * @param modelId
	 * @return
	 */
	public String getProcessModelEditorJson(String modelId);

	/**
	 * 验证流程
	 * 
	 * @param json_xml
	 */
	public void validationProcess(byte[] json_xml);

}
