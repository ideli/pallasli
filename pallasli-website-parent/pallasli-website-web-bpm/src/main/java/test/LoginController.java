package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/auth")
public class LoginController implements HttpSessionListener {

	/**
	 * 用户和Session绑定关系
	 */
	public static final Map<String, HttpSession> USER_SESSION = new HashMap<String, HttpSession>();

	/**
	 * seeionId和用户的绑定关系
	 */
	public static final Map<String, String> SESSIONID_USER = new HashMap<String, String>();

	/**
	 * 实现HttpSessionListener接口监听,监听session的销毁事件
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		// 当前session销毁时删除当前session绑定的用户信息
		// 同时删除当前session绑定用户的HttpSession
		USER_SESSION.remove(SESSIONID_USER.remove(sessionId));
		System.out.println("销毁session:" + sessionId);
	}

	/**
	 * 实现HttpSessionListener接口监听,监听session的创建事件
	 */
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		String sessionId = se.getSession().getId();
		System.out.println("创建session:" + sessionId);
	}

	/**
	 * 用户登录时的处理 处理一个账号同时只有一个地方登录的关键
	 * 
	 * @param request
	 */
	public static void userLoginHandle(HttpServletRequest request) {
		// 当前登录的用户
		String userName = request.getParameter("userName");
		// 当前sessionId
		String sessionId = request.getSession().getId();
		// 删除当前sessionId绑定的用户，用户--HttpSession
		USER_SESSION.remove(SESSIONID_USER.remove(sessionId));

		// 删除当前登录用户绑定的HttpSession
		HttpSession session = USER_SESSION.remove(userName);
		if (session != null) {
			SESSIONID_USER.remove(session.getId());
			session.removeAttribute("userName");
			session.setAttribute("userMsg", "您的账号已经在另一处登录了,你被迫下线!");
		}
	}

	/**
	 * 用户登录
	 */
	@RequestMapping(value = "relogin", method = RequestMethod.GET)
	public String relogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		if (session != null) {
			USER_SESSION.remove(SESSIONID_USER.remove(session.getId()));
			session.invalidate();
		}
		if (userName != null && !"".equals(userName))
			System.out.println("用户[" + userName + "]下线");
		return "login.jsp";
	}

	/**
	 * 用户登录
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		if (userName != null && !"".equals(userName.trim())) {
			// 登录成功
			if (login(userName, password)) {
				// 处理用户登录(保持同一时间同一账号只能在一处登录)
				userLoginHandle(request);
				// 添加用户与HttpSession的绑定
				USER_SESSION.put(userName.trim(), session);
				// 添加sessionId和用户的绑定
				SESSIONID_USER.put(session.getId(), userName);
				System.out.println("用户[" + userName + "]已上线");
				session.setAttribute("userName", userName);
				session.removeAttribute("userMsg");
				response.sendRedirect("main.jsp");
			} else {
				System.out.println("用户[" + userName + "]登录失败");
				request.setAttribute("msg", "登录失败,请重新登录");
				// response.sendRedirect("login.jsp");
				request.getRequestDispatcher("login.jsp").forward(request,
						response);
				// 注意：上面的代码如果没有放在try中,将无法传递参数msg
			}
		} else {
			System.out.println("用户[" + userName + "]登录失败");
			request.setAttribute("msg", "登录失败,请重新登录");
			// response.sendRedirect("login.jsp");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
			// 注意：上面的代码如果没有放在try中,将无法传递参数msg给jsp
		}
		return "home";
	}

	// 对比用户输入的信息是否合法，从而判断是否登录成功
	private boolean login(String userName, String password) {
		return "lyt".equals(userName);
	}

}