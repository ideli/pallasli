package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class EmailAddress extends IFieldEditor {

	public JsonObject getEditor(Field field){
		JsonObject json = new JsonObject();

		json.addProperty("applicationkey", IFieldEditor.getApplicationKey());
		json.addProperty("xtype", "mixkyemailaddress");
		json.addProperty("name", field.getF_key());
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("anchor", "100%");
		
		JsonObjectTool.applyJson(json, field.getF_config());
		
		return json;
	}
}
