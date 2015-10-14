package com.pallas.design.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonFunction;
import com.google.gson.JsonObject;
import com.pallas.design.bean.CompConfig;
import com.pallas.design.bean.CompType;
import com.pallas.design.bean.Menu;
import com.pallas.design.bean.MenuType;
import com.pallas.design.bean.PageType;
import com.pallas.design.dao.CompTypeDAO;
import com.pallas.design.dao.MenuDAO;
import com.pallas.design.dao.MenuTypeDAO;
import com.pallas.design.dao.PageTypeDAO;
import com.pallas.design.service.SysService;

public class SysServiceImpl implements SysService {
	private MenuDAO menuDao;
	private MenuTypeDAO menuTypeDao;
	private PageTypeDAO pageTypeDao;
	private CompTypeDAO compTypeDao;

	public MenuDAO getMenuDao() {
		return menuDao;
	}

	public void setMenuDao(MenuDAO menuDao) {
		this.menuDao = menuDao;
	}

	public MenuTypeDAO getMenuTypeDao() {
		return menuTypeDao;
	}

	public void setMenuTypeDao(MenuTypeDAO menuTypeDao) {
		this.menuTypeDao = menuTypeDao;
	}

	public PageTypeDAO getPageTypeDao() {
		return pageTypeDao;
	}

	public void setPageTypeDao(PageTypeDAO pageTypeDao) {
		this.pageTypeDao = pageTypeDao;
	}

	public CompTypeDAO getCompTypeDao() {
		return compTypeDao;
	}

	public void setCompTypeDao(CompTypeDAO compTypeDao) {
		this.compTypeDao = compTypeDao;
	}

	public List<CompType> getCompTypes() {
		List<CompType> list = compTypeDao.selectAll();
		return list;
	}

	public JsonObject saveCompType(CompConfig compType) {
		JsonObject json = new JsonObject();

		return json;
	}

	@Override
	public Menu saveMenu(Menu menu) {
		if (menu.getId() == null || menu.getId() < 0) {
			menu = menuDao.insert(menu);

		} else {
			menuDao.update(menu);

		}
		return menu;
	}

	@Override
	public boolean deleteMenu(Menu menu) {

		Menu toDelete = menuDao.select(menu.getId());

		return menuDao.delete(toDelete);
	}

	@Override
	public List<Menu> loadSysMenu() {
		List<Menu> list = menuDao.selectSysMenus();
		return list;
	}

	@Override
	public List<Menu> loadSysMenu(Menu sysMenu) {
		List<Menu> list = menuDao.selectMenusByParent(sysMenu);
		return list;
	}

	@Override
	public List<Menu> loadModuleMenu(Menu sysMenu) {
		List<Menu> list = menuDao.selectMenusByParent(sysMenu);
		return list;
	}

	@Override
	public List<Menu> selectAll() {
		List<Menu> list = menuDao.selectAll();
		return list;
	}

	@Override
	public List<MenuType> loadMenuTypes() {
		List<MenuType> list = menuTypeDao.selectAll();
		return list;
	}

	@Override
	public List<PageType> loadPageTypes() {
		List<PageType> list = pageTypeDao.selectAll();
		return list;
	}

	@Override
	public Menu getMenu(long id) {
		Menu menu = menuDao.select(id);
		return menu;
	}

	@Override
	public Menu getMenuByKey(String key) {
		Menu menu = menuDao.selectByKey(key);
		return menu;
	}

	@Override
	public List<Menu> selectAllMenuGroups() {

		return menuDao.selectAllMenuGroups();
	}

	private void addChildMenuItems(Menu menu, JsonObject json) {
		if (!menu.getMenuTypeCode().equals("10")
				&& !menu.getMenuTypeCode().equals("20")) {
			List<Menu> list = menuDao.selectMenusByParent(menu);
			for (Menu child : list) {
				JsonObject item = new Gson().toJsonTree(child)
						.getAsJsonObject();
				item.remove("id");
				JsonFunction fn = new JsonFunction("sysMenuAction");
				item.add("handler", fn);
				item.addProperty("text", item.get("menuCaption").getAsString());
				if (json.has("items")) {
					json.get("items").getAsJsonArray().add(item);
				} else {
					if (!json.has("menu")) {
						json.add("menu", new JsonArray());
					}
					json.get("menu").getAsJsonArray().add(item);

				}

				addChildMenuItems(child, item);

			}
		} else {
			// JsonObject item = new Gson().toJsonTree(menu).getAsJsonObject();
			// if (!json.has("children")) {
			// json.add("children", new JsonArray());
			// }
			// json.get("children").getAsJsonArray().add(item);
		}
	}

	public void selectDeepChildSysMenus(Menu menu, List<Menu> ret) {
		ret.add(menu);
		if (!menu.getMenuTypeCode().equals("10")
				&& !menu.getMenuTypeCode().equals("20")) {
			List<Menu> list = menuDao.selectMenusByParent(menu);
			for (Menu child : list) {
				ret.add(child);
				selectDeepChildSysMenus(child, ret);

			}
		}
	}

	@Override
	public List<Menu> selectDeepChildSysMenus(Menu menu) {
		List<Menu> ret = new ArrayList<Menu>();
		selectDeepChildSysMenus(menu, ret);
		return ret;
	}

	@Override
	public JsonObject getDeepChildSysMenus(Menu menu) {
		JsonObject json = null;
		json = new Gson().toJsonTree(menu).getAsJsonObject();
		json.remove("id");
		json.add("items", new JsonArray());
		json.addProperty("xtype", "buttongroup");
		addChildMenuItems(menu, json);

		return json;
	}
}
