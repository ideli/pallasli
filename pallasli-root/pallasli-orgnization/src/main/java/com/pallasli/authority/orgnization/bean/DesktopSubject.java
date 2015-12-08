package com.pallasli.authority.orgnization.bean;

import com.google.gson.JsonObject;

public class DesktopSubject {
	String applicationkey;							// 栏目名
	String key;							// 栏目名
	String text;						// 栏目名称
	String iconCls;						// 栏目名称
	int width = 300;					// 栏目宽度
	int height = 300;					// 栏目高度
	int webheight = 300;				// 栏目高度
	int top = 50;						// 栏目顶部位置
	int left = 50;						// 栏目左侧位置
	int col = -1;						// 列
	int row = -1;						// 行
	
	
	public String getApplicationkey() {
		return applicationkey;
	}
	public void setApplicationkey(String applicationkey) {
		this.applicationkey = applicationkey;
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
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}

	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getWebheight() {
		return webheight;
	}
	public void setWebheight(int webheight) {
		this.webheight = webheight;
	}
	/**
	 * 从JSON对象初始化值
	 * @param json
	 */
	public void fromJsonObject(JsonObject json){
		if(json != null){
			if(json.has("applicationkey")){
				applicationkey = json.get("applicationkey").getAsString();
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
			if(json.has("width")){
				width = json.get("width").getAsInt();
			}
			if(json.has("height")){
				height = json.get("height").getAsInt();
			}
			if(json.has("top")){
				top = json.get("top").getAsInt();
			}
			if(json.has("left")){
				left = json.get("left").getAsInt();
			}
			if(json.has("col")){
				col = json.get("col").getAsInt();
			}
			if(json.has("row")){
				row = json.get("row").getAsInt();
			}
			if(json.has("webheight")){
				webheight = json.get("webheight").getAsInt();
			}
		}
	}
	/**
	 * 生成Json对象
	 * @return
	 */
	public JsonObject toJsonObject(){
		JsonObject json = new JsonObject();
		if(applicationkey != null){
			json.addProperty("applicationkey", applicationkey);
		}
		json.addProperty("key", key);
		json.addProperty("text", text);
		json.addProperty("iconCls", iconCls);
		json.addProperty("width", width);
		json.addProperty("height", height);
		json.addProperty("top", top);
		json.addProperty("left", left);
		if(col >= 0){
			json.addProperty("col", col);
		}
		if(row >= 0){
			json.addProperty("row", row);
		}
		json.addProperty("webheight", webheight);
		return json;
	}
	/**
	 * 生成Json字符串
	 */
	public String toString(){
		return toJsonObject().toString();
	}
}
