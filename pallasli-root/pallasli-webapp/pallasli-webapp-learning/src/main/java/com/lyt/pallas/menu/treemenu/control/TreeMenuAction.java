package com.lyt.pallas.menu.treemenu.control;

import java.util.List;

import com.lyt.pallas.menu.treemenu.manage.TreeMenuService;
import com.lyt.pallas.menu.treemenu.model.TreeMenu;

public class TreeMenuAction {

	public TreeMenuService treeMenuService = new TreeMenuService();

	public List<TreeMenu> getTreeMenu(long parentId) {

		return treeMenuService.getTreeMenu(parentId);
	}
}
