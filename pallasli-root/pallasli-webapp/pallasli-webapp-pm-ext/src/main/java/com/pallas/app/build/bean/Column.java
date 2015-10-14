package com.pallas.app.build.bean;

import com.google.gson.JsonObject;

public class Column {
	private String f_key;
	private String f_name;
	private String f_caption;
	private String f_type;
	private int f_flex;
	private int f_length;
	private JsonObject f_config;

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

	public String getF_type() {
		return f_type;
	}

	public void setF_type(String f_type) {
		this.f_type = f_type;
	}

	public int getF_flex() {
		return f_flex;
	}

	public void setF_flex(int f_flex) {
		this.f_flex = f_flex;
	}

	public int getF_length() {
		return f_length;
	}

	public void setF_length(int f_length) {
		this.f_length = f_length;
	}

	public JsonObject getF_config() {
		return f_config;
	}

	public void setF_config(JsonObject f_config) {
		this.f_config = f_config;
	}
}
