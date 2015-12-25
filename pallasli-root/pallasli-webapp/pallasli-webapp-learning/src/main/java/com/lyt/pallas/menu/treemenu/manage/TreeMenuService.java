package com.lyt.pallas.menu.treemenu.manage;

import java.util.List;

import com.lyt.pallas.menu.treemenu.dao.TreeMenuDAO;
import com.lyt.pallas.menu.treemenu.model.TreeMenu;

public class TreeMenuService {
	public TreeMenuDAO treeMenuDao = new TreeMenuDAO();

	public List<TreeMenu> getTreeMenu(long parentId) {
		return treeMenuDao.findAllByParentId(parentId);
	}
}
