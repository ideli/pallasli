package com.pallasli.designer.rest.inerfacesetting;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.design.bean.Fieldset;
import com.pallas.design.bean.Menu;
import com.pallas.design.bean.MenuType;
import com.pallas.design.bean.Page;
import com.pallas.design.bean.PageType;
import com.pallas.design.bean.Table;
import com.pallas.design.service.FieldsetService;
import com.pallas.design.service.PageService;
import com.pallas.design.service.SysService;
import com.pallas.design.service.TableService;
import com.pallasli.constant.SystemConstant;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

@Controller(value = "interfaceSetting")
@RequestMapping("/interfacesetting")
public class InterfaceSetting {

	private final SysService sysService;
	private final TableService tableService;
	private final FieldsetService fieldsetService;
	private final PageService pageService;

	public SysService getSysService() {
		return sysService;
	}

	public TableService getTableService() {
		return tableService;
	}

	public FieldsetService getFieldsetService() {
		return fieldsetService;
	}

	public InterfaceSetting() {

		ApplicationContext ctx = new FileSystemXmlApplicationContext("/"
				+ SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		sysService = (SysService) ctx.getBean("sysService");
		tableService = (TableService) ctx.getBean("tableService");
		pageService = (PageService) ctx.getBean("pageService");
		fieldsetService = (FieldsetService) ctx.getBean("fieldsetService");
	}

	@DirectMethod(method = "loadSysMenu")
	public List<Menu> loadSysMenu(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		List<Menu> list = sysService.loadSysMenu();
		return list;
	}

	@DirectMethod(method = "loadMenu")
	public Menu loadMenu(long id) {
		Menu menu = sysService.getMenu(id);
		return menu;
	}

	@DirectMethod(method = "loadMenus")
	public List<Menu> loadMenus() {
		List<Menu> list = sysService.selectAll();
		return list;
	}

	@DirectMethod(method = "loadSysMenus")
	public JsonArray loadSysMenus() {
		List<Menu> menuGrouplist = sysService.selectAllMenuGroups();
		JsonArray list = new JsonArray();
		for (Menu menu : menuGrouplist) {
			JsonObject childMenuList = sysService.getDeepChildSysMenus(menu);
			list.add(childMenuList);
		}
		return list;
	}

	@DirectMethod(method = "loadModuleTreeMenus")
	public JsonArray loadModuleTreeMenus(String key, String appKey,
			boolean isRoot, int childType, String menuTableName) {
		Menu sysMenu = sysService.getMenuByKey(key);

		JsonArray array = new JsonArray();

		if (isRoot) {
			JsonObject jsonC = new JsonObject();
			jsonC.addProperty("appKey", "pallas_design");
			jsonC.addProperty("menuId", sysMenu.getId());
			jsonC.addProperty("text", sysMenu.getMenuCaption());
			jsonC.addProperty("menuKey", sysMenu.getMenuKey());
			jsonC.addProperty("urlPath", sysMenu.getUrlPath());
			jsonC.addProperty("expanded", true);
			jsonC.addProperty("leaf", false);
			array.add(jsonC);
			return array;
		} else if (childType == 1) {
			// childType==1 普通加载
			List<Menu> list = sysService.loadModuleMenu(sysMenu);
			for (Menu menu : list) {

				JsonObject jsonC = new JsonObject();
				jsonC.addProperty("appKey", "pallas_design");
				jsonC.addProperty("menuId", menu.getId());
				jsonC.addProperty("text", menu.getMenuCaption());
				jsonC.addProperty("urlPath", menu.getUrlPath());
				jsonC.addProperty("menuKey", menu.getMenuKey());
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

		} else if (childType == 0) {
			// childType==2 同类数据加载--表数据方式
			if (menuTableName.equals("Table")) {
				List<Table> list = tableService.loadTables();
				for (Table table : list) {

					JsonObject jsonC = new JsonObject();
					jsonC.addProperty("appKey", "pallas_design");
					jsonC.addProperty("text", table.getTableCaption());
					jsonC.addProperty("menuKey", sysMenu.getMenuKey()
							+ ".m_field");
					jsonC.addProperty("nodeName", table.getTableName());
					jsonC.addProperty("tableName", table.getTableName());
					jsonC.addProperty("urlPath", sysMenu.getChildUrlPath());
					jsonC.addProperty("pageTypeCode", sysMenu.getPageTypeCode());
					jsonC.addProperty("expanded", false);
					jsonC.addProperty("leaf", true);
					array.add(jsonC);
				}
			} else if (menuTableName.equals("Fieldset")) {
				List<Fieldset> list = fieldsetService.loadFieldsets();
				for (Fieldset bean : list) {

					JsonObject jsonC = new JsonObject();
					jsonC.addProperty("appKey", "pallas_design");
					jsonC.addProperty("text", bean.getFieldsetCaption());
					jsonC.addProperty("menuKey", sysMenu.getMenuKey()
							+ ".m_group");
					jsonC.addProperty("nodeName", bean.getFieldsetName());
					jsonC.addProperty("fieldsetName", bean.getFieldsetName());
					jsonC.addProperty("urlPath", sysMenu.getChildUrlPath());
					jsonC.addProperty("pageTypeCode", sysMenu.getPageTypeCode());
					jsonC.addProperty("expanded", false);
					jsonC.addProperty("leaf", true);
					array.add(jsonC);
				}
			} else if (menuTableName.equals("Page")) {
				List<Page> list = pageService.loadPages();
				for (Page bean : list) {

					JsonObject jsonC = new JsonObject();
					jsonC.addProperty("appKey", "pallas_design");
					jsonC.addProperty("text", bean.getPageCaption());
					jsonC.addProperty("menuKey", sysMenu.getMenuKey()
							+ ".m_page");
					jsonC.addProperty("nodeName", bean.getPageName());
					jsonC.addProperty("pageName", bean.getPageName());
					jsonC.addProperty("urlPath", sysMenu.getChildUrlPath());
					jsonC.addProperty("pageTypeCode", sysMenu.getPageTypeCode());
					jsonC.addProperty("expanded", false);
					jsonC.addProperty("leaf", true);
					array.add(jsonC);
				}
			}
		}
		return array;
	}

	@DirectMethod(method = "saveMenu")
	public JsonObject saveMenu(Menu menu) {

		menu = sysService.saveMenu(menu);

		return new JsonObject();

	}

	@DirectMethod(method = "deleteMenu")
	public JsonObject deleteMenu(JsonArray data) {
		JsonObject addNode = data.get(0).getAsJsonObject();
		long id = addNode.has("id") ? addNode.get("id").getAsLong() : -1l;
		Menu menu = new Menu();
		menu.setId(id);
		boolean success = sysService.deleteMenu(menu);
		JsonObject json = new JsonObject();
		json.addProperty("success", success);
		return json;
	}

	@DirectMethod(method = "loadMenuTypes")
	public List<MenuType> loadMenuTypes() {
		List<MenuType> list = sysService.loadMenuTypes();
		return list;
	}

	@DirectMethod(method = "loadPageTypes")
	public List<PageType> loadPageTypes() {
		List<PageType> list = sysService.loadPageTypes();
		return list;
	}

}
