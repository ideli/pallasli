package com.pallasli.component;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class MultiSelectField extends IFieldEditor{
	
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		
		json.addProperty("name", field.getF_key());
		json.addProperty("xtype", "multiselect");
		//json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", field.getF_caption());
		//json.addProperty("allowBlank", false);
		//json.addProperty("autoScroll", true);
		
		JsonObjectTool.applyJson(json, field.getF_config());
		
		if(field.getF_config() != null && !field.getF_config().isJsonNull()&& field.getF_config().has("store")){
			String store = field.getF_config().get("store").getAsString();
			json.add("store", new JsonFunction(store));
		}
		if(field.getF_config() != null && !field.getF_config().isJsonNull()&& field.getF_config().has("tbar")){
			String tbar = field.getF_config().get("tbar").getAsString();
			json.add("tbar", new JsonFunction(tbar));
		}
		if(field.getF_config() != null && !field.getF_config().isJsonNull()&& field.getF_config().has("afterrender")){
			JsonObject render = new JsonObject();
			String afterrender = field.getF_config().get("afterrender").getAsString();
			render.add("afterrender", new JsonFunction(afterrender));
			json.add("listeners", render);
			json.remove("afterrender");
		}
		json.addProperty("ddReorder", true);
		return json;
   }

}
