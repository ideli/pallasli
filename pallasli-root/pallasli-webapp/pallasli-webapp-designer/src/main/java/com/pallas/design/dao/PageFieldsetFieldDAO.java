package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.PageFieldsetField;

public interface PageFieldsetFieldDAO {
	List<PageFieldsetField> selectAll();

	PageFieldsetField insert(PageFieldsetField pageFieldsetField);

	boolean update(PageFieldsetField pageFieldsetField);

	boolean delete(PageFieldsetField pageFieldsetField);
}
