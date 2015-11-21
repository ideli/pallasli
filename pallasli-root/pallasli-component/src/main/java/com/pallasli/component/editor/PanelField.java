package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class PanelField extends IFieldEditor {

	public JsonObject getEditor(Field field){
		JsonObject json = new JsonObject();
		
		json.addProperty("applicationkey", IFieldEditor.getApplicationKey());
		json.addProperty("xtype", "panel");
		json.addProperty("anchor", "100%");
		json.addProperty("name", field.getF_key());
		json.addProperty("border",false);
		json.addProperty("fieldLabel", field.getF_caption());
		json.addProperty("editable", false);
		
		JsonObjectTool.applyJson(json, field.getF_config());
		
		return json;
	}
}