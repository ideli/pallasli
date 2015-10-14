package com.pallas.designer.design.bean;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class AuthGrid extends AuthComp {
	private JsonArray f_columns;
	private JsonObject f_api;
	private String f_modelName;
	private JsonObject f_extraParams;
	private JsonArray f_buttons;

	public JsonArray getF_columns() {
		return f_columns;
	}

	public void setF_columns(JsonArray f_columns) {
		this.f_columns = f_columns;
	}

	public JsonObject getF_api() {
		return f_api;
	}

	public void setF_api(JsonObject f_api) {
		this.f_api = f_api;
	}

	public String getF_modelName() {
		return f_modelName;
	}

	public void setF_modelName(String f_modelName) {
		this.f_modelName = f_modelName;
	}

	public JsonObject getF_extraParams() {
		return f_extraParams;
	}

	public void setF_extraParams(JsonObject f_extraParams) {
		this.f_extraParams = f_extraParams;
	}

	public JsonArray getF_buttons() {
		return f_buttons;
	}

	public void setF_buttons(JsonArray f_buttons) {
		this.f_buttons = f_buttons;
	}

}
