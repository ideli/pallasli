package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.MenuType;

public interface MenuTypeDAO {

	List<MenuType> selectAll();

	MenuType insert(MenuType menuType);

	boolean update(MenuType menuType);

	boolean delete(MenuType menuType);

}
