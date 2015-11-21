package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class TriggerField extends IFieldEditor{
	
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		String applicationkey = this.getApplicationKey(field);
		json.addProperty("xtype", "trigger");
		json.addProperty("name", field.getF_key());
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("anchor", "100%");
		json.addProperty("triggerClass", "x-form-search-trigger");

		if(field.getF_config() != null && !field.getF_config().isJsonNull() && field.getF_config().has("triggerclick")){
		String fn = field.getF_config().get("triggerclick").getAsString();
		json.add("onTriggerClick", new JsonFunction(fn));
		}


		JsonObjectTool.applyJson(json, field.getF_config());
		json.addProperty("applicationkey", applicationkey);
		json.remove("triggerclick");
		return json;
   }

}
