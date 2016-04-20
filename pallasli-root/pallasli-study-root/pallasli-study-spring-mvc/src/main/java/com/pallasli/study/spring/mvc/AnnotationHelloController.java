package com.pallasli.study.spring.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/annotation")
public class AnnotationHelloController {

	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "hello world";
	}
}
