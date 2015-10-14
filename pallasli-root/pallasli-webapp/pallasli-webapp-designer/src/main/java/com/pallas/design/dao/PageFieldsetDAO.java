package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.PageFieldset;

public interface PageFieldsetDAO {
	List<PageFieldset> selectAll();

	PageFieldset insert(PageFieldset pageFieldset);

	boolean update(PageFieldset pageFieldset);

	boolean delete(PageFieldset pageFieldset);
}
