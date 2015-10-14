package com.pallas.sys.direct;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.sys.bean.Menu;
import com.pallas.sys.direct.action.MenuAction;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class ModuleMenuDirectAction {
	public MenuAction menuAction = new MenuAction();

	@DirectMethod(method = "loadSysMenu")
	public List<Menu> loadSysMenu(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		List<Menu> list = menuAction.loadSysMenu();
		return list;
	}

	@DirectMethod(method = "loadMenu")
	public Menu loadMenu(long id) {
		Menu menu = menuAction.getMenu(id);
		return menu;
	}

	@DirectMethod(method = "loadModuleTreeMenus")
	public JsonArray loadModuleTreeMenus(String key, boolean isRoot,
			int childType, String menuTableName) {

		JsonArray array = new JsonArray();
		if (isRoot) {
			key = "";
			List<Menu> list = menuAction.getChildMenu(key);
			for (Menu sysMenu : list) {
				JsonObject jsonC = new JsonObject();
				jsonC.addProperty("menuId", sysMenu.getId());
				jsonC.addProperty("text", sysMenu.getMenuCaption());
				jsonC.addProperty("menuCaption", sysMenu.getMenuCaption());
				jsonC.addProperty("menuKey", sysMenu.getMenuKey());
				jsonC.addProperty("appKey", sysMenu.getAppKey());
				jsonC.addProperty("urlPath", sysMenu.getUrlPath());
				jsonC.addProperty("expanded", true);
				jsonC.addProperty("leaf", false);
				array.add(jsonC);
			}
			return array;
		}
		Menu sysMenu = new Menu();
		sysMenu.setMenuKey(key);
		List<Menu> list = menuAction.loadModuleMenu(sysMenu);
		for (Menu menu : list) {

			JsonObject jsonC = new JsonObject();
			jsonC.addProperty("menuId", menu.getId());
			jsonC.addProperty("text", menu.getMenuCaption());
			jsonC.addProperty("urlPath", menu.getUrlPath());
			jsonC.addProperty("appKey", menu.getAppKey());
			jsonC.addProperty("menuKey", menu.getMenuKey());
			jsonC.addProperty("menuCaption", menu.getMenuCaption());
			jsonC.addProperty("menuTypeCode", menu.getMenuTypeCode());
			jsonC.addProperty("menuTableName", menu.getMenuTableName());
			jsonC.addProperty("menuWhereSql", menu.getMenuWhereSql());
			if (menu.getMenuTypeCode().endsWith("11")
					|| menu.getMenuTypeCode().endsWith("40")) {
				jsonC.addProperty("expanded", true);
				jsonC.addProperty("leaf", false);

			} else {
				jsonC.addProperty("expanded", false);
				jsonC.addProperty("leaf", true);
			}
			array.add(jsonC);
		}

		return array;
	}

	@DirectMethod(method = "saveMenu")
	public JsonObject saveMenu(Menu menu) {

		menu = menuAction.saveMenu(menu);

		return new JsonObject();

	}

	@DirectMethod(method = "deleteMenu")
	public JsonObject deleteMenu(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		long id = addNode.has("id") ? addNode.get("id").getAsLong() : -1l;
		Menu menu = new Menu();
		menu.setId(id);
		boolean success = menuAction.deleteMenu(menu);
		JsonObject json = new JsonObject();
		json.addProperty("success", success);
		return json;
	}

}
