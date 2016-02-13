package com.hello;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class Error implements ErrorController{

    @RequestMapping("/error")
    public String error() {
        return "pages/404.jsp";
    }

	public String getErrorPath() {
		// TODO Auto-generated method stub
		return "/error";
	}

}
