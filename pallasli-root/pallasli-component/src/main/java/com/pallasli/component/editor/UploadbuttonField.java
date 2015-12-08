package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.pallasli.component.Field;
import com.pallasli.component.IFieldEditor;
import com.pallasli.component.JsonObjectTool;

public class UploadbuttonField extends IFieldEditor {

	@Override
	public JsonObject getEditor(Field field) {
		JsonObject json = new JsonObject();
		json.addProperty("xtype", "mixkyuploadbutton");
		json.addProperty("text", field.getF_caption());

		JsonObjectTool.applyJson(json, field.getF_config());

		return json;
	}
}
