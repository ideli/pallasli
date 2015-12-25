package physics.app.control.tab.systemmanage;

import java.util.List;

import com.pallasli.utils.MashalUtil;
import com.lyt.pallas.web.physics.control.AbstractTabAction;

public class TreeMenuAction extends AbstractTabAction {
	public String getMenus(long id) {
		List<com.lyt.pallas.menu.treemenu.model.TreeMenu> list = new com.lyt.pallas.menu.treemenu.control.TreeMenuAction()
				.getTreeMenu(id);
		return MashalUtil.marshal(list);
	}
}
