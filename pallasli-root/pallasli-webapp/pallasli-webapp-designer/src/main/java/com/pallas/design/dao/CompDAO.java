package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Comp;

public interface CompDAO {

	List<Comp> selectCompsByTypeId(Long typeId);

	List<Comp> selectAll();

	Comp insert(Comp comp);

	boolean update(Comp comp);

	boolean delete(Comp comp);

}
