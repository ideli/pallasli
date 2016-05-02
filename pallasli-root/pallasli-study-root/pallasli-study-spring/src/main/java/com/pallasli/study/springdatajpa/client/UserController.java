package com.pallasli.study.springdatajpa.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pallasli.study.springdatajpa.User;

@Controller
@RequestMapping("/users")
public class UserController {

	@RequestMapping("/{id}")
	public String showUserForm(@PathVariable("id") User user, Model model) {

		model.addAttribute("user", user);
		return "userForm";
	}
}
