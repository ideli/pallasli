package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.CompConfig;

public interface CompConfigDAO {

	List<CompConfig> selectAll();

	CompConfig insert(CompConfig compConfig);

	boolean update(CompConfig compConfig);

	boolean delete(CompConfig compConfig);
}
