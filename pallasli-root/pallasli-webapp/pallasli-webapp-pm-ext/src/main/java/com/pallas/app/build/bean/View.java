package com.pallas.app.build.bean;

import com.google.gson.JsonObject;

public class View {
	private String f_key;
	private String f_name;
	private String f_caption;
	private JsonObject f_config;
	private Column[] f_columns;
	private Action[] f_actions;

	public String getF_key() {
		return f_key;
	}

	public void setF_key(String f_key) {
		this.f_key = f_key;
	}

	public String getF_name() {
		return f_name;
	}

	public void setF_name(String f_name) {
		this.f_name = f_name;
	}

	public String getF_caption() {
		return f_caption;
	}

	public void setF_caption(String f_caption) {
		this.f_caption = f_caption;
	}

	public JsonObject getF_config() {
		return f_config;
	}

	public void setF_config(JsonObject f_config) {
		this.f_config = f_config;
	}

	public Column[] getF_columns() {
		return f_columns;
	}

	public void setF_columns(Column[] f_columns) {
		this.f_columns = f_columns;
	}

	public Action[] getF_actions() {
		return f_actions;
	}

	public void setF_actions(Action[] f_actions) {
		this.f_actions = f_actions;
	}
}
