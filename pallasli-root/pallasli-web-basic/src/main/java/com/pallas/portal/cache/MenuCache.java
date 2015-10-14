package com.pallas.portal.cache;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class MenuCache {
	private static JsonArray sysMenus = new JsonArray();
	private static JsonArray appMenus = new JsonArray();
	private static JsonArray moduleMenus = new JsonArray();
	public static MenuCache menuCache;

	public static MenuCache instance() {
		if (menuCache == null) {
			menuCache = new MenuCache();
			JsonObject sysmenu = new JsonObject();
			sysmenu.addProperty("text", "sysmenu");
			sysMenus.add(sysmenu);
			JsonObject appmenu = new JsonObject();
			appmenu.addProperty("text", "appmenu");
			appMenus.add(appmenu);
		}
		return menuCache;
	}

	public JsonArray getSysMenus() {
		return sysMenus;
	}

	public void setSysMenus(JsonArray sysMenus) {
		MenuCache.sysMenus = sysMenus;
	}

	public JsonArray getAppMenus() {
		return appMenus;
	}

	public void setAppMenus(JsonArray appMenus) {
		MenuCache.appMenus = appMenus;
	}

	public JsonArray getModuleMenus() {
		return moduleMenus;
	}

	public void setModuleMenus(JsonArray moduleMenus) {
		MenuCache.moduleMenus = moduleMenus;
	}

	public MenuCache getMenuCache() {
		return menuCache;
	}

	public void setMenuCache(MenuCache menuCache) {
		MenuCache.menuCache = menuCache;
	}

}
