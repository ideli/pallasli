package com.pallas.design.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.CompConfig;
import com.pallas.design.bean.CompType;
import com.pallas.design.bean.Menu;
import com.pallas.design.bean.MenuType;
import com.pallas.design.bean.PageType;

public interface SysService {
	List<CompType> getCompTypes();

	JsonObject saveCompType(CompConfig compType);

	List<Menu> loadSysMenu();

	List<Menu> loadModuleMenu(Menu sysMenu);

	List<Menu> loadSysMenu(Menu sysMenu);

	Menu saveMenu(Menu menu);

	boolean deleteMenu(Menu menu);

	List<Menu> selectAll();

	List<MenuType> loadMenuTypes();

	Menu getMenu(long id);

	Menu getMenuByKey(String key);

	List<PageType> loadPageTypes();

	/**
	 * 查询所有的菜单分组
	 * 
	 * @return
	 */
	List<Menu> selectAllMenuGroups();

	/**
	 * 深度查询系统菜单类型的子菜单
	 * 
	 * @param menu
	 * @return
	 */
	List<Menu> selectDeepChildSysMenus(Menu menu);

	JsonObject getDeepChildSysMenus(Menu menu);
}
