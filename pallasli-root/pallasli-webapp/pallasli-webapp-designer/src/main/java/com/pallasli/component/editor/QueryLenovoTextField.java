package com.pallasli.component.editor;

import com.google.gson.JsonObject;
import com.pallasli.component.Field;
import com.pallasli.component.JsonObjectTool;

public class QueryLenovoTextField extends IQueryEditor {

	@Override
	public JsonObject getEditor(Field column) {
		JsonObject json = new JsonObject();
		json.addProperty("name", column.getF_key());
		json.addProperty("xtype", "datefield");
		json.addProperty("anchor", "100%");
		json.addProperty("fieldLabel", column.getF_caption());
		json.addProperty("format", "Y-m-d");
		json.addProperty("altFormats", "Y-m-d H:i:s|m/d/Y|Ymd|Y-m-d");
		String str = "";
		json.addProperty("value", str);
		JsonObjectTool.applyJson(json, column.getF_config());
		return json;
	}

	@Override
	public void getQueryString(Field column, JsonObject params, String sql) {
	}
}
