package com.pallas.ws.sys;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import com.pallas.ws.sys.bean.Menu;

@WebService(serviceName = "MenuService", targetNamespace = "http://ws.ai.wasoft.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class MenuService {
	@WebMethod
	public List<Menu> loadSysMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public Menu getMenu(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public List<Menu> loadModuleMenu(Menu sysMenu) {
		Menu menu = new Menu();
		List<Menu> list = new ArrayList<Menu>();
		menu.setId(12l);
		menu.setMenuCaption("流程定义管理");
		menu.setMenuTypeCode("01");
		list.add(menu);
		return list;
	}

	@WebMethod
	public Menu saveMenu(Menu menu) {
		// TODO Auto-generated method stub
		return null;
	}

	@WebMethod
	public boolean deleteMenu(Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}

	@WebMethod
	public Menu getMenu(String key) {
		Menu menu = new Menu();
		menu.setId(11l);
		menu.setMenuCaption("流程管理");
		return menu;
	}

}
