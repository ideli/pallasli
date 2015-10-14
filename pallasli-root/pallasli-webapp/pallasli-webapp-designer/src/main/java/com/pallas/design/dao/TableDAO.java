package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Table;

public interface TableDAO {

	public List<Table> selectTablesByPrefixion(Table params);

	List<Table> selectAll();

	Table insert(Table table);

	boolean update(Table table);

	boolean delete(Table table);

	public void save(List<Table> list);

	public Table load(Table t);

	public Table loadByTableName(String tableName);

	public Table loadByProjectTableName(String projectName, String tableName);

}
