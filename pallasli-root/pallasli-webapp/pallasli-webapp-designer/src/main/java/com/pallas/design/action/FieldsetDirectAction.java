package com.pallas.design.action;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Config;
import com.pallas.design.bean.Fieldset;
import com.pallas.design.bean.FieldsetField;
import com.pallas.design.service.FieldsetService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class FieldsetDirectAction {
	private final FieldsetService fieldsetService;

	public FieldsetService getWordService() {
		return fieldsetService;
	}

	public FieldsetDirectAction() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
				SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		fieldsetService = (FieldsetService) ctx.getBean("fieldsetService");
	}

	@DirectMethod(method = "getFieldSets")
	public List<Fieldset> getFieldSets(Fieldset params) {
		List<Fieldset> list = fieldsetService.loadFieldsets();
		return list;
	}

	@DirectMethod(method = "getFieldSetFields")
	public List<FieldsetField> getFieldSetFields(Fieldset fieldset) {
		List<FieldsetField> list = fieldsetService.getFieldSetFields(fieldset);
		return list;
	}

	@DirectMethod(method = "getFieldsetFieldPropetyValues")
	public List<Config> getFieldsetFieldPropetyValues(
			FieldsetField fieldsetField) {
		List<Config> list = fieldsetService
				.getFieldSetFieldPropetyValues(fieldsetField);
		return list;
	}

	@DirectMethod(method = "getFieldsetPropetyValues")
	public List<Config> getFieldsetPropetyValues(Fieldset fieldset) {
		List<Config> list = fieldsetService.getFieldSetPropetyValues(fieldset);
		return list;
	}

	@DirectMethod(method = "saveFieldSet")
	public JsonObject saveFieldSet(Fieldset fieldset) {

		return fieldsetService.saveFieldSet(fieldset);
	}

	@DirectMethod(method = "importFieldToFieldSet")
	public JsonObject importFieldToFieldSet(List<FieldsetField> fieldsetFields) {
		JsonObject result = new JsonObject();
		for (FieldsetField fieldsetField : fieldsetFields) {
			// FieldsetField fieldsetField = new FieldsetField();
			// String projectName = map.get("projectName").toString();
			// String fieldsetName = map.get("fieldsetName").toString();
			// String fieldName = map.get("fieldName").toString();
			// String fieldCaption = map.get("fieldCaption").toString();
			// fieldsetField.setProjectName(projectName);
			// fieldsetField.setFieldName(fieldName);
			// fieldsetField.setFieldCaption(fieldCaption);
			// fieldsetField.setFieldsetName(fieldsetName);
			fieldsetService.saveFieldSetField(fieldsetField);
		}
		result.addProperty("success", true);
		return result;
	}

}
