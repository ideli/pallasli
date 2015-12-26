package com.pallas.portal.direct;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.portal.cache.MenuCache;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class SysNavigationDirect {
	@DirectMethod(method = "loadMenus")
	public JsonObject loadMenus() {

		JsonObject result = new JsonObject();

		/*
		 * user userrole rolemodule getMenu(menus,rolemodule)
		 */

		JsonArray sysMenus = MenuCache.instance().getSysMenus();
		JsonArray appMenus = MenuCache.instance().getAppMenus();

		result.add("sysMenus", sysMenus);
		result.add("appMenus", appMenus);

		return result;
	}
}
