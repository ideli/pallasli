package com.pallas.portal.direct;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.portal.bean.Menu;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class ModuleNavigationDirect {
	@DirectMethod(method = "loadMenus")
	public JsonArray loadMenus(String key, boolean isRoot, int childType,
			String menuTableName) {

		JsonArray array = new JsonArray();
		if (isRoot) {
			key = "";
			Menu sysMenu = new Menu();
			sysMenu.setId(1l);
			sysMenu.setMenuCaption("menuCaption");
			sysMenu.setMenuKey("menuKey");
			sysMenu.setUrlPath("urlPath");

			JsonObject jsonC = new JsonObject();
			jsonC.addProperty("menuId", sysMenu.getId());
			jsonC.addProperty("text", sysMenu.getMenuCaption());
			jsonC.addProperty("menuCaption", sysMenu.getMenuCaption());
			jsonC.addProperty("menuKey", sysMenu.getMenuKey());
			jsonC.addProperty("urlPath", sysMenu.getUrlPath());
			jsonC.addProperty("expanded", true);
			jsonC.addProperty("leaf", false);
			array.add(jsonC);
			return array;
		}
		Menu sysMenu = new Menu();
		sysMenu.setMenuKey(key);
		List<Menu> list = new ArrayList<Menu>();

		Menu child = new Menu();
		child.setId(11l);
		child.setMenuCaption("menuCaption");
		child.setMenuKey("menuKey");
		child.setUrlPath("urlPath");
		child.setMenuTypeCode("01");

		list.add(child);
		child = new Menu();
		child.setId(12l);
		child.setMenuCaption("menuCaption1");
		child.setMenuKey("menuKey1");
		child.setUrlPath("urlPath1");
		child.setMenuTypeCode("01");
		list.add(child);

		for (Menu menu : list) {

			JsonObject jsonC = new JsonObject();
			jsonC.addProperty("menuId", menu.getId());
			jsonC.addProperty("text", menu.getMenuCaption());
			jsonC.addProperty("urlPath", menu.getUrlPath());
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
}
