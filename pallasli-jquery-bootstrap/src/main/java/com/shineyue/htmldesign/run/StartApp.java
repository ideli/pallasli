package com.shineyue.htmldesign.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;

import com.shineyue.htmldesign.contoller.ComponentController;
import com.shineyue.htmldesign.contoller.MenuController;
import com.shineyue.htmldesign.contoller.PageComponentController;
import com.shineyue.htmldesign.contoller.SimpleController;
import com.shineyue.htmldesign.page.contoller.PageRouteController;

@SpringApplicationConfiguration(classes = StartApp.class)
public class StartApp {
	public static void main(String[] args) {

		SpringApplication.run(new Object[] { SimpleController.class,
				MenuController.class, PageComponentController.class,
				ComponentController.class, PageRouteController.class }, args);

	}
}
