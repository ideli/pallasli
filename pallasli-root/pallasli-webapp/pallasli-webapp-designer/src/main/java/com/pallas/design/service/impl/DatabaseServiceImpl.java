package com.pallas.design.service.impl;

import java.util.List;

import com.pallas.design.bean.Database;
import com.pallas.design.bean.Table;
import com.pallas.design.bean.TableField;
import com.pallas.design.dao.DatabaseDAO;
import com.pallas.design.dao.TableDAO;
import com.pallas.design.dao.TableFieldDAO;
import com.pallas.design.service.DatabaseService;

public class DatabaseServiceImpl implements DatabaseService {
	private TableDAO tableDao;
	private TableFieldDAO tableFieldDao;
	private DatabaseDAO databaseDao;

	public TableDAO getTableDao() {
		return tableDao;
	}

	public void setTableDao(TableDAO tableDao) {
		this.tableDao = tableDao;
	}

	public TableFieldDAO getTableFieldDao() {
		return tableFieldDao;
	}

	public void setTableFieldDao(TableFieldDAO tableFieldDao) {
		this.tableFieldDao = tableFieldDao;
	}

	public DatabaseDAO getDatabaseDao() {
		return databaseDao;
	}

	public void setDatabaseDao(DatabaseDAO databaseDao) {
		this.databaseDao = databaseDao;
	}

	@Override
	public void saveDesignTables(List<Table> list) {
		tableDao.save(list);

	}

	@Override
	public void saveDesignTableFields(List<TableField> list) {
		tableFieldDao.saveTableFieds(list);

	}

	@Override
	public void saveDatabase(Database database) {
		databaseDao.insert(database);

	}

	@Override
	public List<Database> getDatabases() {
		// TODO Auto-generated method stub
		return databaseDao.selectAll();
	}

}
