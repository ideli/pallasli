package com.pallasli.study.bpm.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.pallasli.study.bpm.constant.BPMEnvironment;
import com.pallasli.study.bpm.exception.BPMException;
import com.pallasli.study.bpm.service.ManagerService;

/**
 * 流程管理
 * 
 * @author lyt
 * 
 */
@Transactional
@Service
public class ManagerServiceImpl implements ManagerService {

	private static final String MODEL_ID = "modelId";
	private static final String MODEL_NAME = "name";
	private static final String MODEL_REVISION = "revision";
	private static final String MODEL_DESCRIPTION = "description";
	@Autowired
	protected RepositoryService repositoryService;
	@Autowired
	protected RuntimeService runtimeService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	protected FormService formService;
	@Autowired
	protected HistoryService historyService;
	@Autowired
	protected ManagementService managementService;
	@Autowired
	protected IdentityService identityService;

	@Override
	public List<Model> listProcessModels(String appKey, String keyword,
			int firstResult, int pageSize) {
		List<Model> list = repositoryService.createModelQuery().list();
		return list;
	}

	@Override
	public String createProcessModel(String name, String description,
			String appKey, String processkey) {

		if (StringUtils.isBlank(name)) {
			throw new BPMException("流程名称不能为空");
		}

		if (StringUtils.isBlank(appKey)) {
			throw new BPMException("应用KEY不能为空");
		}

		try {
			// 创建流程
			ObjectMapper objectMapper = new ObjectMapper();
			ObjectNode modelObjectNode = objectMapper.createObjectNode();
			if (StringUtils.isBlank(description)) {
				description = "";
			}
			modelObjectNode.put(MODEL_DESCRIPTION, description.trim());

			Model modelData = repositoryService.newModel();
			modelData.setMetaInfo(modelObjectNode.toString());
			modelData.setName(name.trim());
			modelData.setCategory(appKey);
			modelData.setKey(processkey);
			repositoryService.saveModel(modelData);

			// 初始化流程图模型
			ObjectNode editorNode = objectMapper.createObjectNode();
			editorNode.put("id", "canvas");
			editorNode.put("resourceId", "canvas");
			ObjectNode stencilSetNode = objectMapper.createObjectNode();
			stencilSetNode.put("namespace",
					"http://b3mn.org/stencilset/bpmn2.0#");
			editorNode.put("stencilset", stencilSetNode);
			repositoryService
					.addModelEditorSource(modelData.getId(), editorNode
							.toString().getBytes(BPMEnvironment.BPMN_CHARSET));

			String id = modelData.getId();

			return id;
		} catch (Exception e) {
			throw new BPMException("流程新建出错", e);
		}

		// return null;
	}

	@Override
	public boolean saveProcessModel(String modelId, String processModelJson) {
		repositoryService.getModelEditorSource(modelId);
		return false;
	}

	@Override
	public boolean deleteProcessModel(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String copyProcessModel(String name, String description,
			String appKey, String modelId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deployProcessModel(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unDeployProcessModel(String modelId,
			boolean isForceDeleteAllHistory, String deploymentId) {
		return false;
	}

	@Override
	public String exportProcessModelInfo(String modelId) {

		if (StringUtils.isBlank(modelId)) {
			throw new BPMException("导出的模型编号为空");
		}

		Model model = repositoryService.getModel(modelId);
		String editorSource = getProcessModelEditorJson(modelId);
		String processModelJson = createProcessModelJson(model, editorSource);
		// String processModelXml=createProcessModelXml(model,editorSource);
		return processModelJson;
	}

	public String convertStreamToString(InputStream is) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		return sb.toString();
	}

	@Override
	public InputStream exportProcessModelInfoByDeploymentId(String deploymentId) {
		Deployment d = repositoryService.createDeploymentQuery()
				.deploymentId(deploymentId).singleResult();
		ProcessDefinition prodef = findProcessDefinitionByDeploymentId(deploymentId);
		InputStream processBpmn = repositoryService.getResourceAsStream(
				deploymentId, prodef.getResourceName());
		return processBpmn;
	}

	private ProcessDefinition findProcessDefinitionByDeploymentId(
			String deploymentId) {
		ProcessDefinition prodef = repositoryService
				.createProcessDefinitionQuery().deploymentId(deploymentId)
				.singleResult();
		return prodef;
	}

	private String createProcessModelJson(Model model, String editorSource) {
		String result = "";
		if (model == null) {
			throw new BPMException("未查询到要导出的模型");
		}

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put("category", model.getCategory());
		modelObjectNode.put("key", model.getKey());
		modelObjectNode.put("metaInfo", model.getMetaInfo());
		modelObjectNode.put("name", model.getName());
		modelObjectNode.put("name", model.getName());
		modelObjectNode.put("editorSource", editorSource);
		result = modelObjectNode.toString();

		return result;
	}

	@Override
	public boolean importProcessModelInfo(String processModelJson) {

		String name = "new", description = "new", appKey = "bpm应用", processkey = "new";
		String modelId = createProcessModel(name, description, appKey,
				processkey);
		saveProcessModel(modelId, processModelJson);
		return false;

	}

	@Override
	public String getProcessModelEditorJson(String modelId) {
		byte[] editorSource = repositoryService.getModelEditorSource(modelId);
		String editorSourceJson = null;
		try {
			editorSourceJson = new String(editorSource, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new BPMException("编码转换错误", e);
		}
		return editorSourceJson;
	}

	@Override
	public void validationProcess(byte[] json_xml) {
		// TODO Auto-generated method stub

	}
}
