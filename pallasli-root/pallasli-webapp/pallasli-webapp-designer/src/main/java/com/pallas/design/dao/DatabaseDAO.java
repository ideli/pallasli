package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Database;

public interface DatabaseDAO {

	List<Database> selectAll();

	Database insert(Database config);

	boolean update(Database config);

	boolean delete(Database config);
}
