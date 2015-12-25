package physics.app.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pallasli.utils.StringUtils;
import com.lyt.pallas.basic.web.action.IAction;

public class MainAction implements IAction {

	public void doAction(HttpServletRequest request,
			HttpServletResponse response) {
		String requestPath = request.getRequestURI();
		try {
			response.sendRedirect(StringUtils.replaceLastMark(requestPath,
					"do", "jsp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
