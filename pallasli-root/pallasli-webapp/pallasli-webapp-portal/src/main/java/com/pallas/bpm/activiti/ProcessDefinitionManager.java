package com.pallas.bpm.activiti;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Model;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.pallas.bpm.activiti.bean.ProcessDefinition;

public class ProcessDefinitionManager {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	protected Gson gson = new Gson();

	public ProcessEngine processEngine = ProcessEngineConfiguration
			.createProcessEngineConfigurationFromResourceDefault()
			.buildProcessEngine();
	public TaskService taskService = processEngine.getTaskService();
	public RepositoryService repositoryService = processEngine
			.getRepositoryService();
	public RuntimeService runtimeService = processEngine.getRuntimeService();
	public HistoryService historyService = processEngine.getHistoryService();

	public void createProcessDefinition(ProcessDefinition p) {

		String description = p.getDescription();
		String name = p.getName();
		String key = p.getKey();
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		// editorNode.put("stencilset", stencilSetNode);
		Model modelData = repositoryService.newModel();

		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		description = StringUtils.defaultString(description);
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,
				description);
		modelData.setMetaInfo(modelObjectNode.toString());
		modelData.setName(name);
		modelData.setKey(StringUtils.defaultString(key));

		repositoryService.saveModel(modelData);
		try {
			repositoryService.addModelEditorSource(modelData.getId(),
					editorNode.toString().getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public void saveProcessDefinition(ProcessDefinition p) {
		// TODO Auto-generated method stub

	}

	public boolean deleteProcessDefinition(ProcessDefinition p) {
		String modelId = p.getModelId();
		repositoryService.deleteModel(modelId);
		return true;
	}

	public boolean deployProcessDefinition(ProcessDefinition p) {
		String modelId = p.getModelId();
		Model modelData = repositoryService.getModel(modelId);
		ObjectNode modelNode = null;
		try {
			modelNode = (ObjectNode) new ObjectMapper()
					.readTree(repositoryService.getModelEditorSource(modelData
							.getId()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] bpmnBytes = null;

		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		bpmnBytes = new BpmnXMLConverter().convertToXML(model);

		String processName = modelData.getName() + ".bpmn20.xml";
		repositoryService.createDeployment().name(modelData.getName())
				.addString(processName, new String(bpmnBytes)).deploy();
		return true;
	}

	public void exportProcessDefinition(ProcessDefinition p,
			HttpServletResponse response) {
		String modelId = p.getModelId();
		try {
			Model modelData = repositoryService.getModel(modelId);
			BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
			JsonNode editorNode = new ObjectMapper().readTree(repositoryService
					.getModelEditorSource(modelData.getId()));
			BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
			BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
			byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

			ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
			IOUtils.copy(in, response.getOutputStream());
			String filename = bpmnModel.getMainProcess().getId()
					+ ".bpmn20.xml";
			response.setHeader("Content-Disposition", "attachment; filename="
					+ filename);
			response.flushBuffer();
		} catch (Exception e) {
			logger.error("导出model的xml文件失败：modelId={}", modelId, e);
		}
	}

	public void importProcessDefinition(ProcessDefinition p) {
		// TODO Auto-generated method stub

	}

	public JsonArray list() {
		List<Model> list = repositoryService.createModelQuery().list();
		JsonArray array = gson.toJsonTree(list).getAsJsonArray();
		return array;

	}

	public void exportProcessDefinition(ProcessDefinition p) {
		// TODO Auto-generated method stub

	}

}
