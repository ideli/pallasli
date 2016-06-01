package com.shineyue.htmldesign.contoller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shineyue.htmldesign.model.ComponentType;
import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.service.ComponentTypeService;
import com.shineyue.htmldesign.service.MenuService;
import com.shineyue.htmldesign.service.PageComponentService;
import com.shineyue.htmldesign.service.impl.ComponentTypeServiceImpl;
import com.shineyue.htmldesign.service.impl.MenuServiceImpl;
import com.shineyue.htmldesign.service.impl.PageComponentServiceImpl;
import com.shineyue.utils.AjaxResult;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/common")
public class CommonController {
	MenuService menuService = new MenuServiceImpl();
	PageComponentService pageComponentService = new PageComponentServiceImpl();
	ComponentTypeService componentTypeService = new ComponentTypeServiceImpl();

	@RequestMapping(value = "/loadMenuTypes", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadMenuTypes(HttpServletRequest req) {

		System.out.println(req.getAttribute("u").toString());
		AjaxResult result = new AjaxResult();
		List<Map<String, String>> vdMapList = new ArrayList<Map<String, String>>();
		Map<String, String> vdMap = new HashMap<String, String>();
		vdMap.put("value", "0");
		vdMap.put("display", "模块");
		vdMapList.add(vdMap);
		vdMap = new HashMap<String, String>();
		vdMap.put("value", "1");
		vdMap.put("display", "页面");
		vdMapList.add(vdMap);
		result.setVdMapList(vdMapList);
		return result;
	}

	@RequestMapping(value = "/loadModules", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadModules() {
		List<Module> ret = menuService.listAllModules();

		AjaxResult result = new AjaxResult();
		List<Map<String, String>> vdMapList = new ArrayList<Map<String, String>>();

		for (Module m : ret) {
			Map<String, String> vdMap = new HashMap<String, String>();
			vdMap.put("value", m.getId().toString());
			vdMap.put("display", m.getCaption());
			vdMapList.add(vdMap);
		}
		result.setVdMapList(vdMapList);
		return result;

	}

	@RequestMapping(value = "/loadPages", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadPages() {
		List<Page> ret = menuService.listAllPages();

		AjaxResult result = new AjaxResult();
		List<Map<String, String>> vdMapList = new ArrayList<Map<String, String>>();
		for (Page p : ret) {
			Map<String, String> vdMap = new HashMap<String, String>();
			vdMap.put("value", p.getId().toString());
			vdMap.put("display", p.getCaption());
			vdMapList.add(vdMap);
		}
		result.setVdMapList(vdMapList);
		return result;

	}

	@RequestMapping(value = "/loadPageFieldgroups", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadPageFieldgroups() {
		List<PageComponent> ret = pageComponentService.listAllPageFieldgroups();

		AjaxResult result = new AjaxResult();
		List<Map<String, String>> vdMapList = new ArrayList<Map<String, String>>();
		for (PageComponent p : ret) {
			Map<String, String> vdMap = new HashMap<String, String>();
			vdMap.put("value", p.getId().toString());
			vdMap.put("display", p.getCaption());
			vdMapList.add(vdMap);
		}
		result.setVdMapList(vdMapList);
		return result;

	}

	@RequestMapping(value = "/loadCompTypes", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadCompTypes() {
		List<ComponentType> ret = componentTypeService.listCompTypes();

		AjaxResult result = new AjaxResult();
		List<Map<String, String>> vdMapList = new ArrayList<Map<String, String>>();
		for (ComponentType p : ret) {
			Map<String, String> vdMap = new HashMap<String, String>();
			vdMap.put("value", p.getId().toString());
			vdMap.put("display", p.getCaption());
			vdMapList.add(vdMap);
		}
		result.setVdMapList(vdMapList);
		return result;

	}

}
