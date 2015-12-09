package com.pallasli.spring.boot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@RequestMapping("/ww")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}
