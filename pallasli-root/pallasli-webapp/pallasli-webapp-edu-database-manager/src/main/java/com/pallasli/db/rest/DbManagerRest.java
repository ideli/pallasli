package com.pallasli.db.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/db")
public class DbManagerRest {
	private static final Logger log = LoggerFactory
			.getLogger(DbManagerRest.class);

	@RequestMapping(value = "/databases", method = RequestMethod.GET)
	public void listDatabases() {
		log.debug("{}", "");
	}

	@RequestMapping(value = "/databases/{database}/tables", method = RequestMethod.GET)
	public String listDbTables(@PathVariable String database, Model model) {

		model.addAttribute("code", 0);

		model.addAttribute("contact", 1);

		return "/contact/detail/jmodel";
	}
}
