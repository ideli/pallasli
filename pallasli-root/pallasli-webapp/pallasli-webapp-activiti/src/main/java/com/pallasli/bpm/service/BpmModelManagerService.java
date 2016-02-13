package com.pallasli.bpm.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallasli.bpm.service.bean.EditorModel;
import com.pallasli.bpm.service.bean.ModelInfo;
import com.pallasli.bpm.service.bean.ProcessModelFile;

public interface BpmModelManagerService {
	/**
	 * 创建流程模型
	 * 
	 * @param params
	 *            流程模型初始化参数（name:流程名;description:描述;catalogue:所属分类）
	 * @return 模型ID
	 */
	String createModel(JsonObject params);

	/**
	 * 保存流程模型
	 * 
	 * @param editorModel
	 *            流程模型
	 * @return
	 */
	boolean saveModel(EditorModel editorModel);

	/**
	 * 删除流程模型
	 * 
	 * @param modelId流程模型ID
	 * @return
	 */
	boolean deleteModel(String modelId);

	/**
	 * 复制流程模型
	 * 
	 * @param fromModelId
	 *            要复制的流程模型ID
	 * @param newParams给新的流程模块设置初始化参数
	 * @return 模型ID
	 */
	String copyModel(String fromModelId, JsonObject newParams);

	/**
	 * 验证流程模型的正确性
	 * 
	 * @param processFile
	 *            流程模型二进制数据
	 * @return
	 */
	boolean validateModel(byte[] processFile);

	/**
	 * 查询流程模型
	 * 
	 * @param params
	 *            查询参数(name:模型名称;catalogue:所属分类)
	 * @param pageSize
	 * @param pageNum
	 * @return
	 */
	List<ModelInfo> findModelList(JsonObject params, int pageSize, int pageNum);

	/**
	 * 发布流程
	 * 
	 * @param modelId
	 *            流程模型ID
	 * @return
	 */
	boolean deployModel(String modelId);

	/**
	 * 根据流程模型ID取消流程发布
	 * 
	 * @param modelId流程模型ID
	 * @return
	 */
	boolean unDeployModelByModelId(String modelId);

	/**
	 * 根据流程部署ID取消流程发布
	 * 
	 * @param deploymentId流程部署ID
	 * @return
	 */
	boolean unDeployModelByDeploymentId(String deploymentId);

	/**
	 * 导入流程模型
	 * 
	 * @param processFile
	 *            流程模型文件
	 * @return
	 */
	boolean importModel(ProcessModelFile processFile);

	/**
	 * 导出流程模型
	 * 
	 * @param modelId
	 *            流程模型ID
	 * @return
	 */
	ProcessModelFile exportModel(String modelId);

}
