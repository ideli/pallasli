package com.pallas.app.build.bean;

import com.google.gson.JsonFunction;
import com.google.gson.JsonObject;

public class Action {
	private String f_key;
	private String f_name;
	private String f_caption;
	private JsonFunction f_handler;
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

	public JsonFunction getF_handler() {
		return f_handler;
	}

	public void setF_handler(JsonFunction f_handler) {
		this.f_handler = f_handler;
	}

	public JsonObject getF_config() {
		return f_config;
	}

	public void setF_config(JsonObject f_config) {
		this.f_config = f_config;
	}

}
