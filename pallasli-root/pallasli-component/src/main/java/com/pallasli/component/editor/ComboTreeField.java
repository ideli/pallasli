package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.pallasli.component.Field;
import com.pallasli.component.IFieldEditor;
import com.pallasli.component.JsonObjectTool;

public class ComboTreeField extends IFieldEditor {

	@Override
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		String applicationkey = IFieldEditor.getApplicationKey();
		json.addProperty("xtype", "combotree");
		json.addProperty("anchor", "100%");
		json.addProperty("name", field.getF_key());
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("editable", false);
		json.addProperty("triggerAction", "all");
		json.addProperty("mode", "local");
		json.addProperty("valueField", "value");
		json.addProperty("displayField", "display");

		JsonObjectTool.applyJson(json, field.getF_config());
		json.addProperty("applicationkey", applicationkey);

		return json;
	}
}
