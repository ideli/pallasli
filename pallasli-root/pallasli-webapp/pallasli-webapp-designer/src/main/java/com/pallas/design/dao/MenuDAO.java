package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Menu;

public interface MenuDAO {

	List<Menu> selectSysMenus();

	List<Menu> selectMenusByParent(Menu sysMenu);

	List<Menu> selectAll();

	Menu insert(Menu menu);

	boolean update(Menu menu);

	boolean delete(Menu menu);

	Menu select(Long id);

	Menu selectByKey(String key);

	List<Menu> selectAllMenuGroups();
}
