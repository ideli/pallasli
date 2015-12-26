package com.shineyue.htmldesign.page.contoller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shineyue.htmldesign.model.Module;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/designer")
public class PageRouteController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@ModelAttribute("form") Module form,
			RedirectAttributes attr) {
		form.setCaption("form");
		attr.addAttribute("user", "lyt");
		return "redirect:/designerApp/home.html";
	}
}
