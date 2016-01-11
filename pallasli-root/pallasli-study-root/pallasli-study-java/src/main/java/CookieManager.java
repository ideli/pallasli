import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	/**
	 * @Method: removeAutoLoginCookie
	 * @Description: 删除自动登录cookie，
	 *               JavaWeb中删除cookie的方式就是新创建一个cookie，新创建的cookie与要删除的cookie同名，
	 *               设置新创建的cookie的cookie的有效期设置为0，有效路径与要删除的cookie的有效路径相同
	 * @Anthor:孤傲苍狼
	 *
	 * @param request
	 * @param response
	 */
	private void removeAutoLoginCookie(HttpServletRequest request,
			HttpServletResponse response) {
		// 创建一个名字为autologin的cookie
		Cookie cookie = new Cookie("autologin", "");
		// 将cookie的有效期设置为0，命令浏览器删除该cookie
		cookie.setMaxAge(0);
		// 设置要删除的cookie的path
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
	}

	/**
	 * @Method: sendAutoLoginCookie
	 * @Description: 发送自动登录cookie给客户端浏览器
	 * @Anthor:孤傲苍狼
	 *
	 * @param request
	 * @param response
	 * @param user
	 */
	private void sendAutoLoginCookie(HttpServletRequest request,
			HttpServletResponse response) {
		// user request.getSession().getAttribute("user")
		if (request.getParameter("logintime") != null) {
			int logintime = Integer.parseInt(request.getParameter("logintime"));
			// 创建cookie,cookie的名字是autologin，值是用户登录的用户名和密码，用户名和密码之间使用.进行分割，密码经过md5加密处理
			Cookie cookie = new Cookie("autologin", "user.getUsername()" + "."
					+ "WebUtils.md5(user.getPassword())");
			// 设置cookie的有效期
			cookie.setMaxAge(logintime);
			// 设置cookie的有效路径
			cookie.setPath(request.getContextPath());
			// 将cookie写入到客户端浏览器
			response.addCookie(cookie);
		}
	}
}
