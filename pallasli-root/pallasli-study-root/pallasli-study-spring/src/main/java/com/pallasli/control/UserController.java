package com.pallasli.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.pallasli.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	public void execute() {
		System.out.println("UserController execute...");
		userService.add();
	}
}