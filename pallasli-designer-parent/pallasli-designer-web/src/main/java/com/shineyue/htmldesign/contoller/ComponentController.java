package com.shineyue.htmldesign.contoller;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shineyue.htmldesign.model.ComponentType;
import com.shineyue.htmldesign.model.ComponentTypeConfig;
import com.shineyue.htmldesign.service.ComponentTypeConfigService;
import com.shineyue.htmldesign.service.ComponentTypeService;
import com.shineyue.htmldesign.service.impl.ComponentTypeConfigServiceImpl;
import com.shineyue.htmldesign.service.impl.ComponentTypeServiceImpl;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/component")
public class ComponentController {
	ComponentTypeConfigService compTypeCfgService = new ComponentTypeConfigServiceImpl();
	ComponentTypeService compTypeService = new ComponentTypeServiceImpl();

	@RequestMapping(value = "/listConfigs", method = RequestMethod.GET)
	@ResponseBody
	public List<ComponentTypeConfig> listConfigs() {
		List<ComponentTypeConfig> list = compTypeCfgService.listConfigs();
		return list;
	}

	@RequestMapping(value = "/listComponentTypes", method = RequestMethod.GET)
	@ResponseBody
	public List<ComponentType> listComponentTypes() {
		List<ComponentType> list = compTypeService.listCompTypes();
		return list;
	}
}
