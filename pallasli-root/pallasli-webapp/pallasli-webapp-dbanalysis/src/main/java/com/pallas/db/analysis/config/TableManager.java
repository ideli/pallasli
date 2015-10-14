package com.pallas.db.analysis.config;

import org.h2.table.Table;

public interface TableManager {
	public void saveTable(Table table);

	public void addTable(Table table);

	public void alterTable(Table table);

	public void deleteTable(Table table);
}
