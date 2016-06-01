package com.shineyue.htmldesign.contoller;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shineyue.htmldesign.model.Module;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/module")
public class ModuleController {

	@RequestMapping(value = "/loadModules", method = RequestMethod.GET)
	@ResponseBody
	public List<Module> loadModules() {
		return null;
	}
}
