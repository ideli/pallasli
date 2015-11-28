package com.pallas.bpm.direct;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class BpmFlowDirect {
	/* 获得应用及模块节点列表 */

	public abstract JsonArray getAppModuleTree(String nodeid, String key,
			HttpServletRequest request);

	/* 获得bpm流程数据集合 */

	public abstract JsonObject getBpmFlowList(int size, int start, String sort,
			String dir, String query, JsonObject params,
			HttpServletRequest request);

	/* 流程基本信息保存 */

	public abstract JsonObject processInfoSave(JsonObject params,
			HttpServletRequest request);

	/* 流程删除 */

	public abstract JsonObject processDel(JsonObject params,
			HttpServletRequest request);

	/* 复制流程 */

	public abstract JsonObject copyProcess(JsonObject params,
			HttpServletRequest request);

	/* 导入流程文件 */
	public abstract JsonObject impProcess(Map<String, String> params,
			Map<String, FileItem> fileFields);

	/* 发布流程 */

	public abstract JsonObject deployProcess(JsonObject params,
			HttpServletRequest request);

	/* 取消发布流程 */

	public abstract JsonObject unDeployProcess(JsonObject params,
			HttpServletRequest request);

	/* 获取流程定义列表 */

	public abstract JsonObject getBpmProcessDefinistionList(int size,
			int start, String sort, String dir, String query,
			JsonObject params, HttpServletRequest request);

	/* 激活暂停流程定义 */

	public abstract JsonObject activateOrSuspendProcessDefintion(
			JsonArray items, HttpServletRequest request);

	/* 获取流程实例列表 */

	public abstract JsonObject getBpmProcessInstanceList(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request);

	/* 激活暂停流程实例 */

	public abstract JsonObject activateOrSuspendProcessInstance(
			JsonArray items, HttpServletRequest request);

	/* 获取流程实例详细信息 */

	public abstract JsonObject getProcessInstanceDetailInfo(JsonObject params,
			HttpServletRequest request);

	/* 获取流程实例活动详细信息 */

	public abstract JsonObject getProcessInstanceActivityDetailInfo(int size,
			int start, String sort, String dir, String query,
			JsonObject params, HttpServletRequest request);

	/* 获取流程实例任务详细信息 */
	public abstract JsonObject getProcessInstanceTaskInfo(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request);

	/* 获取流程实例变量详细信息 */
	public abstract JsonObject getProcessInstanceVariableInfo(int size,
			int start, String sort, String dir, String query,
			JsonObject params, HttpServletRequest request);

	/* 获取流程活动图 */
	public abstract JsonObject getProcessDiagram(JsonObject params,
			HttpServletRequest request);

	/* 获取流程事件日志 */
	public abstract JsonObject getProcessInstanceEventLog(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request);

	/* 获取流程作业列表 */
	public abstract JsonObject findJobList(int size, int start, String sort,
			String dir, String query, JsonObject params,
			HttpServletRequest request);

	/* 执行或删除流程作业 */
	public abstract JsonObject executeOrDeleteJob(JsonArray items,
			HttpServletRequest request);

	/* 执行或删除流程作业 */
	public abstract JsonObject unDeployProcessDefinition(JsonArray items,
			HttpServletRequest request);
}