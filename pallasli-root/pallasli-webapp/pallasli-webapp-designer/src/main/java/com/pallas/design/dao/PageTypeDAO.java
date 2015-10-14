package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.PageType;

public interface PageTypeDAO {

	List<PageType> selectAll();

	PageType insert(PageType PageType);

	boolean update(PageType PageType);

	boolean delete(PageType PageType);

}
