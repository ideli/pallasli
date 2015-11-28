package com.pallas.bpm.direct;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class AppDirect {

	private HttpServletRequest getHttpServletRequest() {

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder
				.getRequestAttributes())).getRequest();
		return request;
	}

	public abstract JsonArray getQuickStarts(String nodeId);

	public abstract JsonArray getShortcuts(String nodeId);

	public abstract JsonArray getSubjects(String nodeId);

	public abstract JsonObject getNewTableRecordId(String tablename);

	public abstract JsonArray getModuleOutline(String nodeId, JsonObject params);

	public abstract JsonObject getViewResults(String viewkey, int querytype,
			int limit, int start, String sort, String dir, JsonObject params);

	public abstract JsonObject deleteDocument(String documentkey,
			long documentid);

	public abstract JsonObject getClobContext(String tablename,
			String fieldname, long documentid);

	public abstract JsonObject getBpmButtons(String formkey, long businessId,
			String processUNID, String processInstanceId, int type);

	public abstract JsonObject getPanelButtons(String documentkey,
			long documentid, String unid, String docid, String taskId, int type);

	public abstract JsonObject loadFormData(String documentkey,
			String panelkey, long documentid, JsonObject params);

	public abstract JsonObject setClobContext(String tablename,
			String fieldname, long documentid, String context);

	public abstract JsonObject submitFormData(
			Map<String, String> formParameters, Map<String, FileItem> fileFields);

	public abstract JsonObject submitArchiveFormData(
			Map<String, String> formParameters, Map<String, FileItem> fileFields);

	public abstract JsonObject submitRowForm(String formkey, JsonObject data);

	public abstract JsonObject getDocumentFileList(String documentKey,
			long documentId, String fieldName);

	public abstract JsonObject getDocumentPanelFileList(String documentKey,
			long documentId);

	public abstract JsonObject deleteDocumentFieldFile(String documentKey,
			long documentId, String fieldName, String fileFieldName);

	public abstract JsonObject deleteDocumentFile(long fileId);

	public abstract JsonObject getUserSignDisplay(String value);

	public abstract JsonObject getFieldDisplayOpinion(String documentkey,
			long documentid, String objectKey);

	public abstract JsonObject getFieldDisplaySign(String documentkey,
			long documentid, String objectKey);

	public abstract JsonObject getFieldOpinion(String documentkey,
			long documentid, String objectKey);

	public abstract JsonObject setFieldOpinion(String documentkey,
			long documentid, String opinion, String objectKey);

	public abstract JsonObject setFieldSign(String documentkey,
			long documentid, String objectKey, long signAction);

	public abstract JsonObject getViewSummary(String viewkey, int querytype,
			JsonObject params);

	/*
	 * 获取流程图标信息，用于流程实例查看
	 * 
	 * @param key
	 * 
	 * @param refresh
	 * 
	 * @return
	 */

	public abstract JsonObject getFlowCells(String key, boolean refresh);

	/**
	 * 获取流程意见信息，用于流程实例查看
	 * 
	 * @param flowlogId
	 * @param nodekey
	 * @return
	 */

	public abstract JsonObject getUserOpinionLog(long flowlogId, String nodekey);

	public abstract JsonObject assignFlowInstanceProcessor(long flowlogId,
			String userExpression);

	/* 驱动bpm工作流引擎自动运行到指定环节 */
	public abstract JsonObject runBpmProcess(JsonObject data,
			String documentkey, long documentid, String actiontype);

	/* 驱动bpm工作流引擎自动批量运行流程到指定环节 */
	public abstract JsonObject batchRunBpmProcess(JsonObject data);

	private Object getBpmNewService() {
		return null;
	};

	public abstract JsonObject runBpmNewProcess(JsonObject bpmParams,
			JsonObject businessParams, String documentkey, long documentid,
			String actiontype);

	public abstract JsonObject pushBpmInfo(JsonObject data);

	public abstract JsonObject getRemarkList(JsonObject data);

	public abstract JsonObject openProcessInstance(JsonObject data);

	public abstract JsonObject getProcessDiagram(JsonObject data);

	public abstract JsonObject getUser();
}
