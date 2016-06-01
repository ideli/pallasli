package com.shineyue.htmldesign.contoller;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/page")
public class PageController {
	@RequestMapping(value = "/model", method = RequestMethod.GET)
	@ResponseBody
	public List<Object> loadModels() {
		return null;
	}
}
