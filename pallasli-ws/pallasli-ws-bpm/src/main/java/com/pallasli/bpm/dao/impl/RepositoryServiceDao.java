package com.pallasli.bpm.dao.impl;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pallas.activiti.exception.PallasActivitiExecption;

@Transactional
public class RepositoryServiceDao {

	private static final Logger log = LoggerFactory
			.getLogger(RepositoryServiceDao.class);
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;

	private static final String MODEL_ID = "modelId";
	private static final String MODEL_NAME = "name";
	private static final String MODEL_REVISION = "revision";
	private static final String MODEL_DESCRIPTION = "description";

	public String createProcessDefinition(String key, String name,
			String category, String description) {

		Model modelData = repositoryService.newModel();
		modelData.setName(name);
		modelData.setKey(key);
		modelData.setCategory(category);
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(MODEL_NAME, name);
		modelObjectNode.put(MODEL_REVISION, 1);
		if (description == null) {
			description = "";
		}
		modelObjectNode.put(MODEL_DESCRIPTION, description);
		modelData.setMetaInfo(modelObjectNode.toString());
		repositoryService.saveModel(modelData);
		return modelData.getId();
	}

	public String saveProcessDefinition(String modelId, String key,
			String name, String category, String description,
			JsonNode editorNode) {
		String errorMessage = "";

		try {

			Model modelData = repositoryService.getModel(modelId);
			modelData.setName(name);
			modelData.setKey(key);
			modelData.setCategory(category);
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			modelObjectNode.put(MODEL_NAME, name);
			modelObjectNode.put(MODEL_REVISION, 1);
			if (description == null) {
				description = "";
			}
			modelObjectNode.put(MODEL_DESCRIPTION, description);
			modelData.setMetaInfo(modelObjectNode.toString());

			repositoryService.saveModel(modelData);
			repositoryService.addModelEditorSource(modelData.getId(),
					editorNode.toString().getBytes("utf-8"));

			String id = modelData.getId();

			return id;
		} catch (Exception e) {
			errorMessage = "流程新建出错";
			log.error(errorMessage, e);
			throw new PallasActivitiExecption(errorMessage);
		}
	}

	public boolean deleteProcessModel(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	public String copyProcessModel(String name, String description,
			String modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean importProcessModelInfo(
			com.pallas.activiti.bean.ModelXMLInfo modelXMLInfo,
			boolean isOverride) {
		// TODO Auto-generated method stub
		return false;
	}

	public com.pallas.activiti.bean.ModelXMLInfo exportProcessModelInfo(
			String modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean unDeployProcessModel(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	public Model getProcessDefinition(String modelId) {
		// TODO Auto-generated method stub
		return repositoryService.getModel(modelId);
	}

	public byte[] getEditorSource(String modelId) {
		// TODO Auto-generated method stub
		return repositoryService.getModelEditorSource(modelId);
	}
}
