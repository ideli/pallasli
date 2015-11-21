package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class LenovoTextField1 extends IFieldEditor{
	
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		
		json.addProperty("applicationkey", IFieldEditor.getApplicationKey());
		json.addProperty("name", field.getF_key());
		json.addProperty("xtype", "lenovotextfield1");
		json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("allowBlank", false);
	
		JsonObjectTool.applyJson(json, field.getF_config());
		
		if (field.getF_config() != null && !field.getF_config().isJsonNull()&& field.getF_config().has("directFn")) {
			String fn = field.getF_config().get("directFn").getAsString();
			json.remove("directFn");
			json.add("directFn", new JsonFunction(fn));
		}
		if (field.getF_config() != null && !field.getF_config().isJsonNull()&& field.getF_config().has("paramarry")) {
			String s_cs = field.getF_config().get("paramarry").getAsString();
			json.remove("paramarry");
			json.add("paramarry", new JsonFunction(s_cs));			
		}
		if (field.getF_config() != null && !field.getF_config().isJsonNull()&& field.getF_config().has("store")) {
			String store = field.getF_config().get("store").getAsString();
			json.remove("store");
			json.add("store", new JsonFunction(store));			
		}
		return json;
   }

}
