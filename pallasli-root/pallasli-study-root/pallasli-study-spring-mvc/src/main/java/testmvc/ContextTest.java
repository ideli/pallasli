package testmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
@RequestMapping("/hello")
public class ContextTest {

	// spring注解注入
	// 测试时不用注入方式
	// @Resource
	// private HelloWorldService helloWorldService;

	@RequestMapping("/helloWorld")
	public String toHelloWorld(HttpServletRequest request) {
		System.out.println("执行HelloWorldController toHelloWorld方法");

		// 取得spring 上下文
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(request.getSession().getServletContext());

		// 取得springmvc上下文
		WebApplicationContext mvcContext = RequestContextUtils.getWebApplicationContext(request);

		// 取得spring容器中的bean，不是用的注入方式
		// 在一定的场合下，不能使用注入方式，就可以用这种方法取得bean
		// HelloWorldService helloWorldService =
		// (HelloWorldService)springContext.getBean("helloWorldService");
		// 经测试这两个上下文都能取得bean
		return "index";
	}

}