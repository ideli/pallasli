package com.shineyue.htmldesign.html;

import java.util.List;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractComponent {
	public static final String COMPONENT_PANRL = "panel";
	public static final String COMPONENT_TEXT = "text";
	public static final String COMPONENT_CHECKBOX = "checkbox";
	public static final String COMPONENT_SELECT = "select";
	public static final String COMPONENT_TEXTAREA = "textarea";
	public static final String COMPONENT_EMAIL = "email";
	public static final String COMPONENT_DATE = "date";
	public static final String COMPONENT_DATETIME = "datetime";
	public static final String COMPONENT_FILE = "file";
	public static final String COMPONENT_IMAGE = "image";
	public static final String COMPONENT_LABEL = "label";
	public static final String COMPONENT_LIST = "list";
	public static final String COMPONENT_NUMBER = "number";
	public static final String COMPONENT_RADIO = "radio";
	public static final String COMPONENT_RANGE = "range";
	public static final String COMPONENT_TIME = "time";
	public static final String COMPONENT_URL = "url";
	public static final String COMPONENT_TABLE = "table";
	public static final String COMPONENT_TREE = "tree";
	public static final String COMPONENT_NODE = "node";
	public static final String COMPONENT_HEADER = "header";
	public static final String COMPONENT_ROW = "row";
	public static final String COMPONENT_CELL = "cell";
	public static final String COMPONENT_TAB = "tab";

	public abstract String getType();

	private String html;
	private JsonObject config;

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public JsonObject getConfig() {
		return config;
	}

	public void setConfig(JsonObject config) {
		this.config = config;
	}

	public boolean hasChild() {
		return true;
	}

	public void replaceToken(String token, String data) {
		setHtml(getHtml().replaceAll("\\$\\{" + token + "\\}", data));
	}

	public String getBaseHtml() {
		return Builder.COMPONENT_PROPERTIES.getProperty(getType());
	}

	private List<AbstractComponent> children;

	public List<AbstractComponent> getChildren() {
		return children;
	}

	public void setChildren(List<AbstractComponent> children) {
		this.children = children;
	}

	public String getStringConfigByKey(String key) {
		JsonObject config = getConfig();
		JsonElement ele = config.get(key);
		if (ele != null) {
			return ele.getAsString();
		} else {
			return null;
		}
	}

	public Integer getIntConfigByKey(String key) {
		JsonObject config = getConfig();
		JsonElement ele = config.get(key);
		if (ele != null) {
			return ele.getAsInt();
		} else {
			return null;
		}
	}

	public void matchConfigByKey(String token, String key, Class<?> type) {
		String v = null;
		if (type.equals(Integer.class)) {
			Integer value = getIntConfigByKey(key);
			v = (null == value) ? null : value.toString();
		} else if (type.equals(String.class)) {
			v = getStringConfigByKey(key);
		}
		if (v != null)
			replaceToken(token, v);
	}

	public abstract String buildHtml();

	public abstract void initHtml();
}
