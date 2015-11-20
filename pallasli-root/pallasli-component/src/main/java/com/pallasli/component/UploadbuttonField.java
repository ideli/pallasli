package com.pallasli.component;

import com.google.gson.JsonObject;
import com.mixky.common.json.gson.JsonFunction;
import com.mixky.engine.design.store.Field;
import com.mixky.engine.design.store.IFieldEditor;
import com.mixky.toolkit.JsonObjectTool;

public class UploadbuttonField extends IFieldEditor {
	
	@Override
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		json.addProperty("xtype", "mixkyuploadbutton");
		json.addProperty("text", field.getF_caption());
		
		JsonObjectTool.applyJson(json, field.getF_config());
		
		if (field.getF_config() != null && !field.getF_config().isJsonNull()
				&& field.getF_config().has("uploadConfig")) {
			String fn = field.getF_config().get("uploadConfig").getAsString();
			json.remove("uploadConfig");
			json.add("uploadConfig", new JsonFunction(fn));
		}

		return json;
	}
}
