package physics.app.control.tab.systemmanage.manage;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.menu.treemenu.control.TreeMenuAction;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class MainPageAction extends AbstractTabAction {

	public void findMainPage(Map<?, ?> map, HttpServletRequest request,
			HttpServletResponse response) {

	}

	public String doNewAction(HttpServletRequest request,
			HttpServletResponse response) {
		List<com.lyt.pallas.menu.treemenu.model.TreeMenu> list = new TreeMenuAction()
				.getTreeMenu(1);

		return MashalUtil.marshal(list);
	}
}
