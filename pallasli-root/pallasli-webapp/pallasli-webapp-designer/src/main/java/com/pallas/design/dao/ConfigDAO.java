package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Config;

public interface ConfigDAO {

	List<Config> selectAll();

	Config insert(Config config);

	boolean update(Config config);

	boolean delete(Config config);
}
