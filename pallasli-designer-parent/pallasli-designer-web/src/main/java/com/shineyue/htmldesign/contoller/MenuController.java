package com.shineyue.htmldesign.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shineyue.htmldesign.bean.Menu;
import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.service.MaxIdService;
import com.shineyue.htmldesign.service.MenuService;
import com.shineyue.htmldesign.service.impl.MaxIdServiceImpl;
import com.shineyue.htmldesign.service.impl.MenuServiceImpl;
import com.shineyue.utils.AjaxResult;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/menu")
public class MenuController {

	MenuService menuService = new MenuServiceImpl();
	MaxIdService maxIdService = new MaxIdServiceImpl();

	@RequestMapping(value = "/loadMenu", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> loadMenu() {
		List<Menu> ret = listModule(new Module());
		return ret;
	}

	public List<Menu> listModule(Module parent) {
		List<Menu> ret = new ArrayList<Menu>();
		List<Module> list = menuService.listModules(parent);
		for (Module m : list) {
			Menu menu = new Menu();
			menu.setId(m.getId());
			menu.setCaption(m.getCaption());
			menu.setOrderNum(m.getOrderNum());

			List<Menu> clist = listModule(m);
			clist.addAll(listPage(m));
			menu.setChildren(clist);

			ret.add(menu);
		}

		return ret;
	}

	public List<Menu> listPage(Module parent) {
		List<Menu> ret = new ArrayList<Menu>();
		List<Page> list = menuService.listPages(parent);
		System.out.println(list.size());
		for (Page m : list) {
			Menu menu = new Menu();
			menu.setId(m.getId());
			menu.setCaption(m.getCaption());
			menu.setOrderNum(m.getOrderNum());
			menu.setModuleId(m.getModuleId());
			menu.setPath(m.getPath());
			menu.setSourceName(m.getSourceName());
			System.out.println(m.getSourceName());

			ret.add(menu);
		}
		return ret;
	}

	@RequestMapping(value = "/addModule", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addModule(Module record) {
		AjaxResult result = new AjaxResult();
		record.setId(maxIdService.getMaxId("t_module") + 1);
		menuService.addModule(record);
		return result;
	}

	@RequestMapping(value = "/addPage", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addPage(Page record) {
		AjaxResult result = new AjaxResult();
		record.setId(maxIdService.getMaxId("t_page") + 1);
		menuService.addPage(record);
		return result;
	}

	@RequestMapping(value = "/alterModule", method = RequestMethod.GET)
	@ResponseBody
	public Menu alterModule() {
		return null;
	}

	@RequestMapping(value = "/alterPage", method = RequestMethod.GET)
	@ResponseBody
	public Menu alterPage() {
		return null;
	}

	@RequestMapping(value = "/delModule", method = RequestMethod.GET)
	@ResponseBody
	public Menu delModule() {
		return null;
	}

	@RequestMapping(value = "/delPage", method = RequestMethod.GET)
	@ResponseBody
	public Menu delPage() {
		return null;
	}
}
