package com.pallasli.study.struts2.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class LoginInterceptor
// extends AbstractInterceptor {
		implements Interceptor {
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {

		// 取得请求相关的ActionContext实例
		ActionContext ctx = invocation.getInvocationContext();
		// Map session = ctx.getSession();
		// String user = (String) session.get("user");
		//
		// // 如果没有登陆，或者登陆所有的用户名不是yuewei，都返回重新登陆
		//
		// if (user != null && user.equals("yuewei")) {
		// System.out.println("test");
		// return invocation.invoke();
		// }
		if (false) {
			// 允许继续
			return invocation.invoke();
		}
		ctx.put("tip", "你还没有登录");
		return Action.LOGIN;

	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}

}
