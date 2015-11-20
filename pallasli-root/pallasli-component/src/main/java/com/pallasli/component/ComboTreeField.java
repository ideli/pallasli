package com.pallasli.component;

import com.google.gson.JsonObject;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class ComboTreeField extends IFieldEditor {

	public JsonObject getEditor(Field field){
		JsonObject json = new JsonObject();
		String applicationkey = this.getApplicationKey(field);
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
