package com.pallasli.controller.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pallasli.bean.demo.User;

@RestController
@RequestMapping("/screen")
public class ErrorContoller {

	@RequestMapping("/error")
	public User error() {
		User user = new User();
		user.setName("zhang");
		return user;
	}
}
