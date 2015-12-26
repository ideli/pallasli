package com.pallasli.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Page {

	// @RequestMapping("/page")
	// @ResponseBody
	// public ModelAndView hello() {
	// ModelAndView mv = new ModelAndView("hello");
	// return mv;
	// }

	@RequestMapping("/page")
	public String hello() {
		return "hello";
	}
}
