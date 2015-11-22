package com.pallas.bpm.direct;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mixky.common.database.DataServicePool;
import com.mixky.engine.certification.MixkyUserCertification;
import com.mixky.engine.design.bpm.BpmBaseDirect;
import com.mixky.engine.direct.AppBaseDirect;
import com.mixky.engine.direct.WorkFlowInstanceDirect;
import com.mixky.engine.organization.OrganizationManager;
import com.mixky.engine.organization.UserExpression;
import com.mixky.engine.organization.dao.User;
import com.mixky.engine.workflow.dataservice.InstanceDataService;
import com.mixky.exception.OrganizationException;
import com.mixky.exception.WorkFlowException;
import com.mixky.system.ContextHolder;
import com.softwarementors.extjs.djn.config.annotations.DirectFormPostMethod;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;
import com.wasoft.IM;
import com.wasoft.client.bpmnew.ws.wsimport.BPMNewService;
import com.wasoft.client.bpmnew.ws.wsimport.BPMNewServiceFacade;
import com.wasoft.client.bpmnew.ws.wsimport.FormFieldInfo;
import com.wasoft.client.bpmnew.ws.wsimport.MapInfo;
import com.wasoft.client.bpmnew.ws.wsimport.NodeInfo;
import com.wasoft.client.bpmnew.ws.wsimport.ProcessInstanceInfo;
import com.wasoft.client.bpmnew.ws.wsimport.ProcessRemarkInfo;
import com.wasoft.client.bpmnew.ws.wsimport.ProcessRemarkInfoList;

public class BpmAppDirect extends AppBaseDirect {

	private HttpServletRequest getHttpServletRequest() {

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder
				.getRequestAttributes())).getRequest();
		return request;
	}

	@DirectMethod
	public JsonArray getQuickStarts(String nodeId) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getQuickStarts(nodeId, request);
	}

	@DirectMethod
	public JsonArray getShortcuts(String nodeId) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getShortcuts(nodeId, request);
	}

	@DirectMethod
	public JsonArray getSubjects(String nodeId) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getSubjects(nodeId, request);
	}

	@DirectMethod
	public JsonObject getNewTableRecordId(String tablename) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getNewTableRecordId(tablename);
	}

	@DirectMethod
	public JsonArray getModuleOutline(String nodeId, JsonObject params) {
		HttpServletRequest request = getHttpServletRequest();
		JsonArray menus = super.getModuleOutline(nodeId, params, request);
		return menus;
	}

	@DirectMethod
	public JsonObject getViewResults(String viewkey, int querytype, int limit,
			int start, String sort, String dir, JsonObject params) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getViewResults(viewkey, querytype, limit, start, sort,
				dir, params, request);
	}

	@DirectMethod
	public JsonObject deleteDocument(String documentkey, long documentid) {
		HttpServletRequest request = getHttpServletRequest();
		return super.deleteDocument(documentkey, documentid, request);
	}

	@DirectMethod
	public JsonObject getClobContext(String tablename, String fieldname,
			long documentid) {
		return super.getClobContext(tablename, fieldname, documentid);
	}

	@DirectMethod
	public JsonObject getBpmButtons(String formkey, long businessId,
			String processUNID, String processInstanceId, int type) {
		HttpServletRequest request = getHttpServletRequest();
		JsonObject result = new JsonObject();

		if (processUNID != null && !processUNID.trim().equals("") && type == 0) {
			BpmBaseDirect bpm = new BpmBaseDirect();
			bpm.getBpmButtons(result, formkey, businessId, processUNID,
					processInstanceId, request);
		}
		return result;
	}

	@DirectMethod
	public JsonObject getPanelButtons(String documentkey, long documentid,
			String unid, String docid, String taskId, int type) {
		HttpServletRequest request = getHttpServletRequest();
		JsonObject result = super.getPanelButtons(documentkey, documentid,
				request);
		/*
		 * 获取Bpm流程节点设置的按钮权限
		 * 
		 * @param unid 流程唯一id
		 * 
		 * @param docid 流程实例唯一id
		 */
		if (unid != null && !unid.trim().equals("") && type == 0) {
			try {
				User user = MixkyUserCertification.instance().getUserInfo(
						request);
				BPMNewServiceFacade service = new BPMNewService()
						.getBPMNewServicePort();
				ProcessInstanceInfo processInstance = null;
				processInstance = service.openProcessInstance(user.getGrbh(),
						unid, docid, taskId);
				// 标签权限
				JsonArray panels = (JsonArray) result.get("panels");
				// 按钮权限
				JsonArray buttons = (JsonArray) result.get("buttons");
				List<FormFieldInfo> fieldList = processInstance
						.getCurrentNodeInfoList().getInfos().get(0)
						.getFormFieldList();
				for (int i = 0; i < fieldList.size(); i++) {

				}
				result.remove("buttons");
				result.add("buttons", buttons);
				result.remove("panels");
				result.add("panels", panels);
			} catch (Exception e) {

			}
		}
		return result;
	}

	@DirectMethod
	public JsonObject loadFormData(String documentkey, String panelkey,
			long documentid, JsonObject params) {
		HttpServletRequest request = getHttpServletRequest();
		return super.loadFormData(documentkey, panelkey, documentid, params,
				request);
	}

	@DirectMethod
	public JsonObject setClobContext(String tablename, String fieldname,
			long documentid, String context) {
		HttpServletRequest request = getHttpServletRequest();
		return super.setClobContext(tablename, fieldname, documentid, context);
	}

	@DirectFormPostMethod
	public JsonObject submitFormData(Map<String, String> formParameters,
			Map<String, FileItem> fileFields) {
		HttpServletRequest request = getHttpServletRequest();
		return super.submitFormData(formParameters, fileFields, request);
	}

	@DirectFormPostMethod
	public JsonObject submitArchiveFormData(Map<String, String> formParameters,
			Map<String, FileItem> fileFields) {
		HttpServletRequest request = getHttpServletRequest();

		return super.submitFormData(formParameters, fileFields, request);
	}

	@DirectMethod
	public JsonObject submitRowForm(String formkey, JsonObject data) {
		HttpServletRequest request = getHttpServletRequest();
		return super.submitRowForm(formkey, data, request);
	}

	@DirectMethod
	public JsonObject getDocumentFileList(String documentKey, long documentId,
			String fieldName) {
		return super.getDocumentFileList(documentKey, documentId, fieldName);
	}

	@DirectMethod
	public JsonObject getDocumentPanelFileList(String documentKey,
			long documentId) {
		return super.getDocumentPanelFileList(documentKey, documentId);
	}

	@DirectMethod
	public JsonObject deleteDocumentFieldFile(String documentKey,
			long documentId, String fieldName, String fileFieldName) {
		return super.deleteDocumentFieldFile(documentKey, documentId,
				fieldName, fileFieldName);
	}

	@DirectMethod
	public JsonObject deleteDocumentFile(long fileId) {
		return super.deleteDocumentFile(fileId);
	}

	@DirectMethod
	public JsonObject getUserSignDisplay(String value) {
		return super.getUserSignDisplay(value);
	}

	@DirectMethod
	public JsonObject getFieldDisplayOpinion(String documentkey,
			long documentid, String objectKey) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getFieldDisplayOpinion(documentkey, documentid, objectKey,
				request);
	}

	@DirectMethod
	public JsonObject getFieldDisplaySign(String documentkey, long documentid,
			String objectKey) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getFieldDisplaySign(documentkey, documentid, objectKey,
				request);
	}

	@DirectMethod
	public JsonObject getFieldOpinion(String documentkey, long documentid,
			String objectKey) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getFieldOpinion(documentkey, documentid, objectKey,
				request);
	}

	@DirectMethod
	public JsonObject setFieldOpinion(String documentkey, long documentid,
			String opinion, String objectKey) {
		HttpServletRequest request = getHttpServletRequest();
		return super.setFieldOpinion(documentkey, documentid, opinion,
				objectKey, request);
	}

	@DirectMethod
	public JsonObject setFieldSign(String documentkey, long documentid,
			String objectKey, long signAction) {
		HttpServletRequest request = getHttpServletRequest();
		return super.setFieldSign(documentkey, documentid, objectKey,
				signAction, request);
	}

	@DirectMethod
	public JsonObject getViewSummary(String viewkey, int querytype,
			JsonObject params) {
		HttpServletRequest request = getHttpServletRequest();
		return super.getViewSummary(viewkey, querytype, params, request);
	}

	/*
	 * 获取流程图标信息，用于流程实例查看
	 * 
	 * @param key
	 * 
	 * @param refresh
	 * 
	 * @return
	 */
	@DirectMethod
	public JsonObject getFlowCells(String key, boolean refresh) {
		WorkFlowInstanceDirect wfiDirect = new WorkFlowInstanceDirect();
		return wfiDirect.getFlowCells(key, refresh);
	}

	/**
	 * 获取流程意见信息，用于流程实例查看
	 * 
	 * @param flowlogId
	 * @param nodekey
	 * @return
	 */
	@DirectMethod
	public JsonObject getUserOpinionLog(long flowlogId, String nodekey) {
		WorkFlowInstanceDirect wfiDirect = new WorkFlowInstanceDirect();
		return wfiDirect.getUserOpinionLog(flowlogId, nodekey);
	}

	@DirectMethod
	public JsonObject assignFlowInstanceProcessor(long flowlogId,
			String userExpression) {
		HttpServletRequest request = getHttpServletRequest();
		User user = MixkyUserCertification.instance().getUserInfo(request);
		if (user == null) {
			throw OrganizationException.forUserNotLogin();
		}
		JsonObject json = new JsonObject();
		json.addProperty("success", false);
		try {
			User u = new UserExpression(userExpression).getUser();
			if (u != null && u.getId() > 0) {
				InstanceDataService.instance()
						.admin_assignFlowInstanceProcesor(flowlogId, u.getId(),
								user);
				json.addProperty("success", true);
			} else {
				json.addProperty("message", "指定的用户不存在");
			}
		} catch (WorkFlowException e) {
			json.addProperty("message", e.getMessage());
		} catch (OrganizationException e) {
			json.addProperty("success", e.getMessage());
		}
		return json;
	}

	/* 驱动bpm工作流引擎自动运行到指定环节 */
	@DirectMethod
	public JsonObject runBpmProcess(JsonObject data, String documentkey,
			long documentid, String actiontype) {
		HttpServletRequest request = getHttpServletRequest();
		BpmBaseDirect runbpm = new BpmBaseDirect();
		return runbpm.runBpmProcess(data, documentkey, documentid, actiontype,
				request);
	}

	/* 驱动bpm工作流引擎自动批量运行流程到指定环节 */
	@DirectMethod
	public JsonObject batchRunBpmProcess(JsonObject data) {
		HttpServletRequest request = getHttpServletRequest();
		BpmBaseDirect runbpm = new BpmBaseDirect();
		return runbpm.batchRunBpmProcess(data, request);
	}

	private BPMNewServiceFacade getBpmNewService() {
		return (BPMNewServiceFacade) DataServicePool.instance().getService(
				"bpmnew");// 通过DataServicePool接口方法实现应用的webservice连接
	}

	@DirectMethod
	public JsonObject runBpmNewProcess(JsonObject bpmParams,
			JsonObject businessParams, String documentkey, long documentid,
			String actiontype) {

		HttpServletRequest request = getHttpServletRequest();
		JsonObject result = new JsonObject();
		ProcessInstanceInfo processInstance = null;
		boolean success = false;
		String msg = "";
		User user = MixkyUserCertification.instance().getUserInfo(request);

		JsonObject bpmVariable = bpmParams.get("variables").getAsJsonObject();
		Set<Map.Entry<String, JsonElement>> entrySet = bpmVariable.entrySet();
		Iterator<Map.Entry<String, JsonElement>> it = entrySet.iterator();
		MapInfo map = new MapInfo();
		MapInfo.Variables variables = new MapInfo.Variables();
		List<MapInfo.Variables.Entry> entryList = variables.getEntry();
		while (it.hasNext()) {
			Map.Entry<String, JsonElement> entry = it.next();
			String key = entry.getKey();
			String value = "";
			if (key.equals("nextNode")) {
				value = entry.getValue().getAsJsonArray().toString();
			} else {
				value = entry.getValue().getAsString();
			}
			MapInfo.Variables.Entry kv = new MapInfo.Variables.Entry();
			kv.setKey(key);
			kv.setValue(value);
			System.out.println(key);
			System.out.println(value);
			entryList.add(kv);
		}
		map.setVariables(variables);
		String taskId = bpmParams.get("taskId").getAsString();
		if (actiontype.equals("01")) {
			// 办理完成
			BPMNewServiceFacade service = getBpmNewService();
			processInstance = service.completeTask(user.getGrbh(), taskId, map);
			success = true;
			String names = "";
			String nodeName = null;
			List<String> usernames = null;
			if (processInstance.getCurrentNodeInfoList() != null
					&& processInstance.getCurrentNodeInfoList().getInfos()
							.size() > 0) {
				usernames = processInstance.getCurrentNodeInfoList().getInfos()
						.get(0).getHandlerNames();
				nodeName = processInstance.getCurrentNodeInfoList().getInfos()
						.get(0).getName();
				for (String n : usernames) {
					names += ",";
					names += n;
				}
				names.replaceFirst(",", "");
			}

			if (nodeName != null) {

				msg = "流程保存成功<br>已提交到:" + nodeName + "环节<br>处理人:" + names
						+ "<br>";
			} else {
				msg = "流程保存成功";
			}

		} else if (actiontype.equals("02")) {
			// 回退

		} else if (actiontype.equals("03")) {

		} else if (actiontype.equals("04")) {

		} else if (actiontype.equals("05")) {

		}

		result.addProperty("success", success);
		result.addProperty("msg", msg);
		result.add("processInstance", new Gson().toJsonTree(processInstance));
		return result;
	}

	@DirectMethod
	public JsonObject pushBpmInfo(JsonObject data) {
		String appKey = data.get("appKey") == null ? "" : data.get("appKey")
				.getAsString();
		String businessKey = data.get("businessKey") == null ? "" : data.get(
				"businessKey").getAsString();
		String processKey = data.get("processKey") == null ? "" : data.get(
				"processKey").getAsString();
		String formKey = data.get("formKey") == null ? "" : data.get("formKey")
				.getAsString();
		String instanceId = data.get("instanceId") == null ? "" : data.get(
				"instanceId").getAsString();
		String processName = data.get("processName") == null ? "" : data.get(
				"processName").getAsString();

		if (data.get("currentNodeInfoList") == null
				|| data.get("currentNodeInfoList").getAsJsonObject()
						.get("infos").getAsJsonArray().size() == 0) {
			String grbh = data.get("startSummiterId") == null ? "" : data.get(
					"startSummiterId").getAsString();

			List<User> rs = OrganizationManager.instance().getUserListBygrbh(
					grbh);
			String info = processName + "审批完成！";
			if (rs != null && rs.size() > 0) {
				for (int j = 0; j < rs.size(); j++) {
					User u = rs.get(j);
					long uid = u.getId();
					JsonObject json = new JsonObject();
					json.addProperty("unid", processKey);
					json.addProperty("docid", instanceId);
					json.addProperty("appKey", appKey);
					json.addProperty("docKey", formKey);
					json.addProperty("id", "00000000000000000000");
					json.addProperty("documentid", businessKey);
					json.addProperty("msg", info);
					IM.getService().pushCall(uid, "Portal.todoMsg",
							json.toString());
				}
			}

			// 提醒发起者
		} else {
			JsonObject currentNodeInfoList = data.get("currentNodeInfoList")
					.getAsJsonObject();
			JsonArray infos = currentNodeInfoList.get("infos").getAsJsonArray();
			Iterator<JsonElement> iterator = infos.iterator();
			while (iterator.hasNext()) {
				JsonObject node = iterator.next().getAsJsonObject();
				if (node.get("isAutoRun").getAsBoolean()) {
					continue;
				}
				JsonArray grbhs = node.get("handlerIds").getAsJsonArray();
				String taskId = node.get("taskId") == null ? "" : node.get(
						"taskId").getAsString();
				String currentNodeName = node.get("name") == null ? "" : node
						.get("name").getAsString();
				String info = processName + currentNodeName + "需要您办理！";
				Iterator<JsonElement> iterator2 = grbhs.iterator();
				while (iterator2.hasNext()) {
					String grbh = iterator2.next().getAsString();

					List<User> rs = OrganizationManager.instance()
							.getUserListBygrbh(grbh);
					if (rs != null && rs.size() > 0) {
						for (int j = 0; j < rs.size(); j++) {
							User u = rs.get(j);
							long uid = u.getId();
							JsonObject json = new JsonObject();
							json.addProperty("unid", processKey);
							json.addProperty("docid", instanceId);
							json.addProperty("appKey", appKey);
							json.addProperty("docKey", formKey);
							json.addProperty("id", taskId);
							json.addProperty("documentid", businessKey);
							json.addProperty("msg", info);
							IM.getService().pushCall(uid, "Portal.todoMsg",
									json.toString());
						}
					}

				}
			}
		}
		JsonObject result = new JsonObject();
		return result;
	}

	@DirectMethod
	public JsonObject getRemarkList(JsonObject data) {
		HttpServletRequest request = getHttpServletRequest();
		String instanceId = data.get("instanceId").getAsString();

		User user = MixkyUserCertification.instance().getUserInfo(request);
		String userId = user.getGrbh();

		BPMNewServiceFacade service = getBpmNewService();
		ProcessRemarkInfoList list = service.getRemarkList(userId, instanceId);
		List<ProcessRemarkInfo> l = list.getItems();
		JsonArray array = new JsonArray();
		for (ProcessRemarkInfo info : l) {
			array.add(new Gson().toJsonTree(info));
		}
		JsonObject result = new JsonObject();
		result.add("results", array);
		result.addProperty("total", array.size());

		return result;

	}

	@DirectMethod
	public JsonObject openProcessInstance(JsonObject data) {
		HttpServletRequest request = getHttpServletRequest();
		String processInstanceId = data.get("instanceId").getAsString();
		String proccessKey = data.get("key").getAsString();

		User user = MixkyUserCertification.instance().getUserInfo(request);
		BPMNewServiceFacade service = new BPMNewService()
				.getBPMNewServicePort();
		ProcessInstanceInfo processInstance = null;
		processInstance = service.openProcessInstance(user.getGrbh(),
				proccessKey, processInstanceId, "");

		List<NodeInfo> nodes = processInstance.getCurrentNodeInfoList()
				.getInfos();

		JsonArray array = new JsonArray();
		for (NodeInfo info : nodes) {
			array.add(new Gson().toJsonTree(info));
		}
		JsonObject result = new JsonObject();
		result.add("results", array);
		result.addProperty("total", array.size());

		return result;

	}

	@DirectMethod
	public JsonObject getProcessDiagram(JsonObject data) {
		HttpServletRequest request = getHttpServletRequest();
		String processKey = data.get("processKey").getAsString();
		String instanceId = data.get("instanceId").getAsString();

		User user = MixkyUserCertification.instance().getUserInfo(request);
		String userId = user.getGrbh();

		BPMNewServiceFacade service = getBpmNewService();
		byte[] b = service.getProcessDiagram(userId, processKey, instanceId);

		JsonObject result = new JsonObject();
		boolean success = false;

		try {
			String rootPath = ContextHolder.instance().getRealPath("/");
			File dir = new File(rootPath + "processDiagram");
			if (!dir.exists()) {
				dir.mkdir();
			}
			File f = new File(rootPath + "processDiagram/" + instanceId
					+ ".png");
			if (!dir.exists()) {
				dir.createNewFile();
			}
			OutputStream fos = new FileOutputStream(f);
			fos.write(b);
			fos.flush();
			fos.close();
			result.addProperty("src", "/processDiagram/" + instanceId + ".png");
			success = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

		result.addProperty("success", success);
		return result;

	}

	@DirectMethod
	public JsonObject getUser() {
		HttpServletRequest request = getHttpServletRequest();
		User user = MixkyUserCertification.instance().getUserInfo(request);
		String name = user.getF_caption();
		JsonObject result = new JsonObject();
		result.addProperty("user", name);
		return result;

	}
}
