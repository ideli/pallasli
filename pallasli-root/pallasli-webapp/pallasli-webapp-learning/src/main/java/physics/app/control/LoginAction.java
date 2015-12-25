package physics.app.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lyt.pallas.basic.web.action.IAction;
import com.lyt.pallas.menu.treemenu.control.TreeMenuAction;
import com.pallasli.utils.MashalUtil;
import com.pallasli.utils.StringUtils;

public class LoginAction implements IAction {
	@Override
	public void doAction(HttpServletRequest request,
			HttpServletResponse response) {
		String requestPath = request.getRequestURI();
		try {
			response.sendRedirect(StringUtils.replaceLastMark(requestPath,
					"do", "jsp"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	};

	public String doNewAction(HttpServletRequest request,
			HttpServletResponse response) {
		List<com.lyt.pallas.menu.treemenu.model.TreeMenu> list = new TreeMenuAction()
				.getTreeMenu(1);
		// for (com.lyt.pallas.menu.treemenu.model.TreeMenu tm : list) {
		// long orderNum = tm.getOrderNum();
		// long parentId = tm.getParentId();
		// boolean isLeaf = tm.getIsLeaf();

		// }
		return MashalUtil.marshal(list);
	}
}
