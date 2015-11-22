package com.pallas.bpm.direct;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mixky.app.dataservice.BpmFlowDataService;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class BpmFlowDirect {
	/* 获得应用及模块节点列表 */
	@DirectMethod
	public JsonArray getAppModuleTree(String nodeid, String key,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getAppModuleTree(nodeid, key,
				request);
	}

	/* 获得bpm流程数据集合 */
	@DirectMethod
	public JsonObject getBpmFlowList(int size, int start, String sort,
			String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getBpmFlowList(size, start, sort,
				dir, query, params, request);
	}

	/* 流程基本信息保存 */
	@DirectMethod
	public JsonObject processInfoSave(JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().processInfoSave(params, request);
	}

	/* 流程删除 */
	@DirectMethod
	public JsonObject processDel(JsonObject params, HttpServletRequest request) {
		return BpmFlowDataService.instance().processDel(params, request);
	}

	/* 复制流程 */
	@DirectMethod
	public JsonObject copyProcess(JsonObject params, HttpServletRequest request) {
		return BpmFlowDataService.instance().copyProcess(params, request);
	}

	/* 导入流程文件 */
	@DirectFormPostMethod
	public JsonObject impProcess(Map<String, String> params,
			Map<String, FileItem> fileFields) {
		HttpServletRequest request = null;
		return BpmFlowDataService.instance().impProcess(params, fileFields,
				request);
	}

	/* 发布流程 */
	@DirectMethod
	public JsonObject deployProcess(JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().deployProcess(params, request);
	}

	/* 取消发布流程 */
	@DirectMethod
	public JsonObject unDeployProcess(JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().unDeployProcess(params, request);
	}

	/* 获取流程定义列表 */
	@DirectMethod
	public JsonObject getBpmProcessDefinistionList(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getBpmProcessDefinistionList(size,
				start, sort, dir, query, params, request);
	}

	/* 激活暂停流程定义 */
	@DirectMethod
	public JsonObject activateOrSuspendProcessDefintion(JsonArray items,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().activateOrSuspendProcessDefintion(
				items, request);
	}

	/* 获取流程实例列表 */
	@DirectMethod
	public JsonObject getBpmProcessInstanceList(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getBpmProcessInstanceList(size,
				start, sort, dir, query, params, request);
	}

	/* 激活暂停流程实例 */
	@DirectMethod
	public JsonObject activateOrSuspendProcessInstance(JsonArray items,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().activateOrSuspendProcessInstance(
				items, request);
	}

	/* 获取流程实例详细信息 */
	@DirectMethod
	public JsonObject getProcessInstanceDetailInfo(JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getProcessInstanceDetailInfo(
				params, request);
	}

	/* 获取流程实例活动详细信息 */
	@DirectMethod
	public JsonObject getProcessInstanceActivityDetailInfo(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance()
				.getProcessInstanceActivityDetailInfo(size, start, sort, dir,
						query, params, request);
	}

	@DirectMethod
	/* 获取流程实例任务详细信息 */
	public JsonObject getProcessInstanceTaskInfo(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getProcessInstanceTaskInfo(size,
				start, sort, dir, query, params, request);
	}

	@DirectMethod
	/* 获取流程实例变量详细信息 */
	public JsonObject getProcessInstanceVariableInfo(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getProcessInstanceVariableInfo(
				size, start, sort, dir, query, params, request);
	}

	@DirectMethod
	/* 获取流程活动图 */
	public JsonObject getProcessDiagram(JsonObject params,
			HttpServletRequest request) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return BpmFlowDataService.instance().getProcessDiagram(params, request);
	}

	@DirectMethod
	/* 获取流程事件日志 */
	public JsonObject getProcessInstanceEventLog(int size, int start,
			String sort, String dir, String query, JsonObject params,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().getProcessInstanceEventLog(size,
				start, sort, dir, query, params, request);
	}

	@DirectMethod
	/* 获取流程作业列表 */
	public JsonObject findJobList(int size, int start, String sort, String dir,
			String query, JsonObject params, HttpServletRequest request) {
		return BpmFlowDataService.instance().findJobList(size, start, sort,
				dir, query, params, request);
	}

	@DirectMethod
	/* 执行或删除流程作业 */
	public JsonObject executeOrDeleteJob(JsonArray items,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().executeOrDeleteJob(items, request);
	}

	@DirectMethod
	/* 执行或删除流程作业 */
	public JsonObject unDeployProcessDefinition(JsonArray items,
			HttpServletRequest request) {
		return BpmFlowDataService.instance().unDeployProcessDefinition(items,
				request);
	}
}