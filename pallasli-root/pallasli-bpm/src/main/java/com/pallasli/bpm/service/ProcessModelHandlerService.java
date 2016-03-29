package com.pallasli.bpm.service;

import org.activiti.engine.repository.Deployment;

public interface ProcessModelHandlerService {

	/** 导入导出文件类型 */
	public static final int FILE_TYPE_ZIP = 0;
	public static final int FILE_TYPE_XML = 1;
	public static final int FILE_TYPE_JSON = 2;
	/** 导入后处理方式-是否覆盖 */
	public static final boolean IMPOET_TYPE_OVERRIDE = true;
	public static final boolean IMPOET_TYPE_NOT_OVERRIDE = false;
	/** 导入后处理方式-是否发布 */
	public static final boolean IMPOET_TYPE_NOT_DEPLOY = false;
	public static final boolean IMPOET_TYPE_DEPLOY = true;

	/**
	 * 添加活动参与者
	 * 
	 * @param proDefId
	 * @param activityId
	 * @param candidateType
	 */
	public void addActivityCandidate(String proDefId, String activityId, int candidateType);

	/**
	 * 发布流程
	 */
	public Deployment deploy(String xmlData);

	/**
	 * 取消发布
	 */

	/**
	 * 保存流程
	 */

	/**
	 *  设置环节审批人
	 */

	/**
	 * 设置环节意见列表（remark）
	 */

	/**
	 * 设置参与形式及参与人
	 */

	/**
	 * 取消发布
	 */

}
