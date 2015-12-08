package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.pallasli.component.Field;
import com.pallasli.component.IFieldEditor;
import com.pallasli.component.JsonObjectTool;

public class MultiSelectField extends IFieldEditor {

	@Override
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();

		json.addProperty("name", field.getF_key());
		json.addProperty("xtype", "multiselect");
		// json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", field.getF_caption());
		// json.addProperty("allowBlank", false);
		// json.addProperty("autoScroll", true);

		JsonObjectTool.applyJson(json, field.getF_config());

		return json;
	}

}
