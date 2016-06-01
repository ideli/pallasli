package com.shineyue.htmldesign.contoller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/main")
public class MainController {
	@RequestMapping(value = "/*")
	public void main(HttpServletRequest req, HttpServletResponse resp,
			HttpSession session) {
		try {
			req.setAttribute("u", "lyt");
			req.getRequestDispatcher("/common/loadMenuTypes")
					.forward(req, resp);
			System.out.println(resp.getOutputStream());
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
