package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.TableField;

public interface TableFieldDAO {
	List<TableField> selectAll();

	TableField insert(TableField tableField);

	boolean update(TableField tableField);

	boolean delete(TableField tableField);

	List<TableField> selectByTableName(String tableName);

	void saveTableFieds(List<TableField> list);

	TableField loadTableFieldByTableNameAndFieldName(String tableName,
			String fieldName);

	TableField loadByProjectTableFieldName(String projectName,
			String tableName, String fieldName);
}
