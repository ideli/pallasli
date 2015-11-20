package com.pallasli.component;

import com.google.gson.JsonObject;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class BpmOpinionField extends IFieldEditor {
	
	public JsonObject getEditor(Field field){
		JsonObject json = new JsonObject();
		json.addProperty("applicationkey", IFieldEditor.getApplicationKey());
		json.addProperty("xtype", "bpmopinionfield");
		//json.addProperty("anchor", "100%");
		json.addProperty("name", field.getF_key());
		json.addProperty("fieldLabel", field.getF_caption());

		JsonObjectTool.applyJson(json, field.getF_config());
		
		return json;
	}	
	
}