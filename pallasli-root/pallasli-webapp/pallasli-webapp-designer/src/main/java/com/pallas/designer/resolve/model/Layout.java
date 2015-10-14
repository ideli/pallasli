package com.pallas.designer.resolve.model;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.designer.resolve.DesignObject;

public class Layout extends DesignObject {
	private String f_key;
	private String f_component_type;
	private String f_layout;
	private String f_columnWidth;
	private String f_height;
	private String f_width;
	private JsonObject f_config;
	private List<Layout> children;

	public String getF_key() {
		return f_key;
	}

	public void setF_key(String f_key) {
		this.f_key = f_key;
	}

	public String getF_component_type() {
		return f_component_type;
	}

	public void setF_component_type(String f_component_type) {
		this.f_component_type = f_component_type;
	}

	public String getF_layout() {
		return f_layout;
	}

	public void setF_layout(String f_layout) {
		this.f_layout = f_layout;
	}

	public String getF_columnWidth() {
		return f_columnWidth;
	}

	public void setF_columnWidth(String f_columnWidth) {
		this.f_columnWidth = f_columnWidth;
	}

	public String getF_height() {
		return f_height;
	}

	public void setF_height(String f_height) {
		this.f_height = f_height;
	}

	public String getF_width() {
		return f_width;
	}

	public void setF_width(String f_width) {
		this.f_width = f_width;
	}

	public JsonObject getF_config() {
		return f_config;
	}

	public void setF_config(JsonObject f_config) {
		this.f_config = f_config;
	}

	public List<Layout> getChildren() {
		return children;
	}

	public void setChildren(List<Layout> children) {
		this.children = children;
	}

}
