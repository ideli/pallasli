package org.activiti.rest.explorer.application;

import org.activiti.editor.constants.ModelDataJsonConstants;
import org.restlet.data.Status;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ModelEditorJsonEx extends ServerResource implements
		ModelDataJsonConstants {

	protected static final Logger LOGGER = LoggerFactory
			.getLogger(ModelEditorJsonEx.class);

	@Get("json")
	public ObjectNode getEditorJson() {

		ObjectNode modelNode = null;

		try {

			ObjectMapper objectMapper = new ObjectMapper();

			String modelId = (String) getRequest().getAttributes().get(
					"modelId");
			// BPMNewServiceFacade bpmService=(BPMNewServiceFacade)
			// DataServicePool.instance().getService("bpmnew");
			// ProcessModelEditorJson
			// editorJson=bpmService.getProcessModelEditorJson(modelId);

			// modelNode = objectMapper.createObjectNode();
			// modelNode.put(MODEL_NAME, editorJson.getName());
			// modelNode.put(MODEL_ID, editorJson.getId());
			// modelNode.put("category", editorJson.getCategory());
			// modelNode.put(MODEL_REVISION, editorJson.getRevision());
			// modelNode.put(MODEL_DESCRIPTION,editorJson.getDescription());
			// modelNode.put("isDeploy", editorJson.getDeployed());
			//
			// ObjectNode editorJsonNode = (ObjectNode)
			// objectMapper.readTree(editorJson.getModel());
			// modelNode.put("model", editorJsonNode);

		} catch (Exception e) {
			LOGGER.error("Error creating model JSON", e);
			setStatus(Status.SERVER_ERROR_INTERNAL);
		}

		return modelNode;
	}
}
