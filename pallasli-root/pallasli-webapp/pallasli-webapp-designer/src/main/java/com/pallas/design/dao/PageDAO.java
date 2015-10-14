package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Page;

public interface PageDAO {
	List<Page> selectAll();

	Page insert(Page page);

	boolean update(Page page);

	boolean delete(Page page);
}
