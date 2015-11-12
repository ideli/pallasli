package com.bpm.service.converter;

import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BaseElement;
import org.activiti.bpmn.model.ExtensionAttribute;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.UserTask;
import org.activiti.editor.language.json.converter.BaseBpmnJsonConverter;
import org.activiti.editor.language.json.converter.UserTaskJsonConverter;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

public class UserTaskJsonConverterEx extends UserTaskJsonConverter {
	public static String NAMESPACE_PREFIX = "pallasli";
	public static String NAMESPACE = "pallasli";
	public static String BPMN_JSON_BUTTON = "atwa_bpmbutton";
	public static String BUTTON = "button";
	public static String BPMN_JSON_INITUSER = "pallali_inituser";
	public static String INITUSER = "inituser";
	public static int TYPE_STRING = 1;
	public static int TYPE_LIST = 2;
	public static int TYPE_NODE = 3;

	public static void fillTypes(
			Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap,
			Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {

		fillJsonTypes(convertersToBpmnMap);
		fillBpmnTypes(convertersToJsonMap);
	}

	public static void fillJsonTypes(
			Map<String, Class<? extends BaseBpmnJsonConverter>> convertersToBpmnMap) {
		convertersToBpmnMap.put(STENCIL_TASK_USER,
				UserTaskJsonConverterEx.class);
	}

	public static void fillBpmnTypes(
			Map<Class<? extends BaseElement>, Class<? extends BaseBpmnJsonConverter>> convertersToJsonMap) {
		convertersToJsonMap.put(UserTask.class, UserTaskJsonConverterEx.class);
	}

	// @Override
	// protected void convertElementToJson(ObjectNode propertiesNode,
	// BaseElement baseElement) {
	// super.convertElementToJson(propertiesNode, baseElement);
	// UserTask userTask = (UserTask) baseElement;
	// setPropertyValue("atwa_bpmbutton",
	// userTask.getAttributeValue(NAMESPACE, "atwa_bpmbutton"),
	// propertiesNode);
	// LoggerFactory.getLogger(getClass()).info("****>{}",
	// userTask.getAttributeValue(NAMESPACE, "atwa_bpmbutton"));
	//
	// }

	protected void addAttribute(UserTask task, JsonNode elementNode,
			String namespacePrefix, String bpmnPropertyName,
			String JsonPropertyName, int type) {
		if (type == TYPE_STRING) {
			String stringValue = getPropertyValueAsString(JsonPropertyName,
					elementNode);
			if (stringValue != null) {
				LoggerFactory.getLogger(getClass())
						.info("---->{}", stringValue);
				ExtensionAttribute extensionAttribute = new ExtensionAttribute(
						NAMESPACE, bpmnPropertyName);
				extensionAttribute.setValue(stringValue);
				extensionAttribute.setNamespacePrefix(namespacePrefix);
				task.addAttribute(extensionAttribute);
			}
		} else if (type == TYPE_LIST) {
			List<String> list = getPropertyValueAsList(JsonPropertyName,
					elementNode);

			if (list != null) {
				LoggerFactory.getLogger(getClass()).info("---->{}", list);
				ExtensionAttribute extensionAttribute = new ExtensionAttribute(
						NAMESPACE, bpmnPropertyName);
				extensionAttribute.setValue(list.toString());
				extensionAttribute.setNamespacePrefix(namespacePrefix);
				task.addAttribute(extensionAttribute);
			}
		} else if (type == TYPE_NODE) {
			JsonNode node = getProperty(JsonPropertyName, elementNode);
			if (node != null) {
				LoggerFactory.getLogger(getClass()).info("---->{}", node);
				ExtensionAttribute extensionAttribute = new ExtensionAttribute(
						NAMESPACE, bpmnPropertyName);
				extensionAttribute.setValue(node.toString());
				extensionAttribute.setNamespacePrefix(namespacePrefix);
				task.addAttribute(extensionAttribute);
			}
		}

	}

	@Override
	protected FlowElement convertJsonToElement(JsonNode elementNode,
			JsonNode modelNode, Map<String, JsonNode> shapeMap) {
		UserTask task = (UserTask) super.convertJsonToElement(elementNode,
				modelNode, shapeMap);
		addAttribute(task, elementNode, NAMESPACE_PREFIX, BUTTON,
				BPMN_JSON_BUTTON, TYPE_STRING);
		addAttribute(task, elementNode, NAMESPACE_PREFIX, INITUSER,
				BPMN_JSON_INITUSER, TYPE_STRING);

		return task;
	}
}
