package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.CompType;

public interface CompTypeDAO {

	List<CompType> selectAll();

	CompType insert(CompType compType);

	boolean update(CompType compType);

	boolean delete(CompType compType);

}
