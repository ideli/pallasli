package com.pallasli.authority.orgnization.bean;

import com.google.gson.JsonObject;

public class DesktopShortcut {

	public static String DS_TYPE_SYSTEM = "sys";
	public static String DS_TYPE_MENU = "menu";
	public static String DS_TYPE_FOLDER = "folder";
	public static String DS_TYPE_DOCUMENT = "document";
	
	private String btntype;
	private String key;
	private String text;
	private String iconCls;
	
	public String getBtntype() {
		return btntype;
	}
	public void setType(String btntype) {
		this.btntype = btntype;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return text;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	/**
	 * 从JSON对象初始化值
	 * @param json
	 */
	public void fromJsonObject(JsonObject json){
		if(json != null){
			if(json.has("btntype")){
				btntype = json.get("btntype").getAsString();
			}
			if(json.has("key")){
				key = json.get("key").getAsString();
			}
			if(json.has("text")){
				text = json.get("text").getAsString();
			}
			if(json.has("iconCls")){
				iconCls = json.get("iconCls").getAsString();
			}
		}
	}
	/**
	 * 生成Json对象
	 * @return
	 */
	public JsonObject toJsonObject(){
		JsonObject json = new JsonObject();
		json.addProperty("btntype", btntype);
		json.addProperty("key", key);
		json.addProperty("text", text);
		json.addProperty("iconCls", iconCls);
		return json;
	}
	/**
	 * 生成Json字符串
	 */
	public String toString(){
		return toJsonObject().toString();
	}
}
