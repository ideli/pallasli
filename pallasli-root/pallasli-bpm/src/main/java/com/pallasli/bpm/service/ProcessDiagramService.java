package com.pallasli.bpm.service;

public interface ProcessDiagramService {
	/**
	 * 获取流程图
	 * 
	 * @param proInstId
	 */
	public void getDiagram(String proDefId);

	/**
	 * 获取流程图，包括流程元素坐标，用于动态显示流程过程
	 * 
	 * @param proInstId
	 */
	public void getDiagramWithCoords(String proDefId);
}
