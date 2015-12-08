package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.pallasli.component.Field;
import com.pallasli.component.IFieldEditor;
import com.pallasli.component.JsonObjectTool;

public class TriggerField extends IFieldEditor {

	@Override
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		String applicationkey = IFieldEditor.getApplicationKey();
		json.addProperty("xtype", "trigger");
		json.addProperty("name", field.getF_key());
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("anchor", "100%");
		json.addProperty("triggerClass", "x-form-search-trigger");

		JsonObjectTool.applyJson(json, field.getF_config());
		json.addProperty("applicationkey", applicationkey);
		json.remove("triggerclick");
		return json;
	}

}
