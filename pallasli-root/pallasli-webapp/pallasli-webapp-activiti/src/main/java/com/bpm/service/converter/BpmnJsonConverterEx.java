package com.bpm.service.converter;

import org.activiti.editor.language.json.converter.BpmnJsonConverter;

public class BpmnJsonConverterEx extends BpmnJsonConverter {
	static {

		UserTaskJsonConverterEx.fillTypes(convertersToBpmnMap,
				convertersToJsonMap);
	}
}
