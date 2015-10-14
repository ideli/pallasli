package com.pallas.design.service;

import java.util.List;

import com.pallas.design.bean.Database;
import com.pallas.design.bean.Table;
import com.pallas.design.bean.TableField;

public interface DatabaseService {

	void saveDesignTables(List<Table> list);

	void saveDesignTableFields(List<TableField> list);

	void saveDatabase(Database database);

	List<Database> getDatabases();

}
