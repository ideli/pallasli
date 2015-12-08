package com.pallasli.authority.orgnization.bean;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class UserConfig {
	
	String key;								// 用户名或默认桌面配置名
	String uimode = "window";				// 用户桌面
	int columns = 2;						// 桌面栏目列数
	String wallpaper = "console";			// 墙纸
	String wallpaperposition = "center";	// 墙纸位置
	String transparency = "100";			// 透明度
	String backgroundcolor = "#FFFFFF";		// 背景色
	String frontcolor =  "#000000";			// 前景色
	String theme;							// 样式风格
	List<DesktopShortcut> shortcuts;		// 桌面按钮
	List<DesktopSubject> subjects;			// 桌面栏目
	List<DesktopShortcut> quickstarts;		// 快速启动
	
	public UserConfig(){
		shortcuts = new ArrayList<DesktopShortcut>();
		subjects = new ArrayList<DesktopSubject>();
		quickstarts = new ArrayList<DesktopShortcut>();
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUimode() {
		return uimode;
	}

	public void setUimode(String uimode) {
		this.uimode = uimode;
	}

	public String getWallpaper() {
		return wallpaper;
	}

	public void setWallpaper(String wallpaper) {
		this.wallpaper = wallpaper;
	}

	public String getWallpaperposition() {
		return wallpaperposition;
	}

	public void setWallpaperposition(String wallpaperposition) {
		this.wallpaperposition = wallpaperposition;
	}

	public String getTransparency() {
		return transparency;
	}

	public void setTransparency(String transparency) {
		this.transparency = transparency;
	}

	public String getBackgroundcolor() {
		return backgroundcolor;
	}

	public void setBackgroundcolor(String backgroundcolor) {
		this.backgroundcolor = backgroundcolor;
	}

	public String getFrontcolor() {
		return frontcolor;
	}

	public void setFrontcolor(String frontcolor) {
		this.frontcolor = frontcolor;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public List<DesktopShortcut> getShortcuts() {
		return shortcuts;
	}

	public void setShortcuts(List<DesktopShortcut> shortcuts) {
		this.shortcuts = shortcuts;
	}

	public List<DesktopSubject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<DesktopSubject> subjects) {
		this.subjects = subjects;
	}

	public List<DesktopShortcut> getQuickstarts() {
		return quickstarts;
	}

	public void setQuickstarts(List<DesktopShortcut> quickstarts) {
		this.quickstarts = quickstarts;
	}
	
	/**
	 * 从Json字符串读取对象值
	 * @param str
	 */
	public void fromString(String str){
		JsonParser parser = new JsonParser();
		JsonObject json = (JsonObject)parser.parse(str);
		fromJsonObject(json);
	}
	/**
	 * 从JSON对象中读取值
	 * @param json
	 */
	public void fromJsonObject(JsonObject json){
		if(json != null){
			if(json.has("key") && !json.get("key").isJsonNull()){
				key = json.get("key").getAsString();
			}
			if(json.has("uimode") && !json.get("uimode").isJsonNull()){
				uimode = json.get("uimode").getAsString();
			}
			if(json.has("columns") && !json.get("columns").isJsonNull()){
				columns = json.get("columns").getAsInt();
			}
			if(json.has("wallpaper") && !json.get("wallpaper").isJsonNull()){
				wallpaper = json.get("wallpaper").getAsString();
			}
			if(json.has("wallpaperposition") && !json.get("wallpaperposition").isJsonNull()){
				wallpaperposition = json.get("wallpaperposition").getAsString();
			}
			if(json.has("transparency") && !json.get("transparency").isJsonNull()){
				transparency = json.get("transparency").getAsString();
			}
			if(json.has("backgroundcolor") && !json.get("backgroundcolor").isJsonNull()){
				backgroundcolor = json.get("backgroundcolor").getAsString();
			}
			if(json.has("frontcolor") && !json.get("frontcolor").isJsonNull()){
				frontcolor = json.get("frontcolor").getAsString();
			}
			if(json.has("theme") && !json.get("theme").isJsonNull()){
				theme = json.get("theme").getAsString();
			}
			if(json.has("shortcuts") && !json.get("shortcuts").isJsonNull()){
				shortcuts.clear();
				JsonArray shortcutArray = json.get("shortcuts").getAsJsonArray();
				for(int i=0;i<shortcutArray.size();i++){
					JsonObject jsonObject = shortcutArray.get(i).getAsJsonObject();
					DesktopShortcut dsc = new DesktopShortcut();
					dsc.fromJsonObject(jsonObject);
					shortcuts.add(dsc);
				}
			}
			if(json.has("subjects") && !json.get("subjects").isJsonNull()){
				subjects.clear();
				JsonArray subjectArray = json.get("subjects").getAsJsonArray();
				for(int i=0;i<subjectArray.size();i++){
					JsonObject jsonObject = subjectArray.get(i).getAsJsonObject();
					DesktopSubject ds = new DesktopSubject();
					ds.fromJsonObject(jsonObject);
					subjects.add(ds);
				}
			}
			if(json.has("quickstarts") && !json.get("quickstarts").isJsonNull()){
				quickstarts.clear();
				JsonArray quickstartArray = json.get("quickstarts").getAsJsonArray();
				for(int i=0;i<quickstartArray.size();i++){
					JsonObject jsonObject = quickstartArray.get(i).getAsJsonObject();
					DesktopShortcut dsc = new DesktopShortcut();
					dsc.fromJsonObject(jsonObject);
					quickstarts.add(dsc);
				}
			}
		}
	}
	
	/**
	 * 生成Json对象
	 * @return
	 */
	public JsonObject toJsonObject(){
		JsonObject json = new JsonObject();
		json.addProperty("key", key);
		json.addProperty("uimode", uimode);
		json.addProperty("columns", columns);
		json.addProperty("wallpaper", wallpaper);
		json.addProperty("wallpaperposition", wallpaperposition);
		json.addProperty("transparency", transparency);
		json.addProperty("backgroundcolor", backgroundcolor);
		json.addProperty("frontcolor", frontcolor);
		json.addProperty("theme", theme);
		// 桌面按钮
		JsonArray shortcutArrays = new JsonArray();
		for(int i=0;i<shortcuts.size();i++){
			DesktopShortcut dsc = shortcuts.get(i);
			shortcutArrays.add(dsc.toJsonObject());
		}
		json.add("shortcuts", shortcutArrays);
		// 桌面栏目
		JsonArray subjectArrays = new JsonArray();
		for(int i=0;i<subjects.size();i++){
			DesktopSubject ds = subjects.get(i);
			subjectArrays.add(ds.toJsonObject());
		}
		json.add("subjects", subjectArrays);
		// 快速启动按钮
		JsonArray quickstartArrays = new JsonArray();
		for(int i=0;i<quickstarts.size();i++){
			DesktopShortcut ds = quickstarts.get(i);
			quickstartArrays.add(ds.toJsonObject());
		}
		json.add("quickstarts", quickstartArrays);
		return json;
	}
	/**
	 * 生成Json字符串
	 */
	public String toString(){
		return toJsonObject().toString();
	}
}
