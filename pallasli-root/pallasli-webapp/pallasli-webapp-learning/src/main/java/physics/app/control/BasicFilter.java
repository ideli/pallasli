package physics.app.control;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyt.pallas.basic.web.action.IAction;
import com.pallasli.utils.StringUtils;

public class BasicFilter implements Filter {
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String requestPath = request.getRequestURI();
		String actionName = "physics.app.control."
				+ StringUtils.alterFirstCharToUpper(requestPath.substring(
						requestPath.lastIndexOf("/") + 1,
						requestPath.lastIndexOf("."))) + "Action";

		Class<?> actionClass = null;
		try {
			actionClass = Class.forName(actionName);

			IAction iAction = (IAction) actionClass.newInstance();
			String rtn = new LoginAction().doNewAction(request, response);
			request.setAttribute("rtn", rtn);
			request.getRequestDispatcher(
					StringUtils.replaceLastMark(requestPath, "do", "jsp")
							.substring(
									requestPath.substring(1).indexOf("/") + 1))
					.forward(request, response);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
