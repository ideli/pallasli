package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.pallasli.component.Field;
import com.pallasli.component.IFieldEditor;
import com.pallasli.component.JsonObjectTool;

public class PanelField extends IFieldEditor {

	@Override
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();

		json.addProperty("applicationkey", IFieldEditor.getApplicationKey());
		json.addProperty("xtype", "panel");
		json.addProperty("anchor", "100%");
		json.addProperty("name", field.getF_key());
		json.addProperty("border", false);
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("editable", false);

		JsonObjectTool.applyJson(json, field.getF_config());

		return json;
	}
}