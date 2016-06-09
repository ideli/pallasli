package com.pallasli.study.bpm.service;

import java.util.List;

import com.pallasli.study.bpm.model.NodeInfo;

public interface ProcessDefinitionService {
	/**
	 * 获取流程图的启动首环节
	 * 
	 * @param processDefId
	 * @return
	 */
	public List<NodeInfo> getFirstNodes(String processDefId);

	/**
	 * 获取指定环节的上一环节
	 * 
	 * @param processDefId
	 * @param curNodeId
	 */
	public List<NodeInfo> getPreNodes(String processDefId, String curNodeId);

	/**
	 * 获取指定环节的下一环节
	 * 
	 * @param processDefId
	 * @param curNodeId
	 */
	public List<NodeInfo> getNextNodes(String processDefId, String curNodeId);

	/**
	 * 获取指定环节的定义信息
	 * 
	 * @param processDefId
	 * @param curNodeId
	 */
	public NodeInfo getNodeInfo(String processDefId, String curNodeId);

	/**
	 * 根据流程定义，获取流程图，加入缓存
	 */
	public void getProcessDiagram(String processDefId);

	/**
	 * 根据流程定义，获取流程图节点位置信息
	 */
	public void getNodeLocation(String processDefId);

}
