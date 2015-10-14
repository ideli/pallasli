package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.FieldsetField;

public interface FieldsetFieldDAO {
	List<FieldsetField> selectAll();

	FieldsetField insert(FieldsetField fieldsetField);

	boolean update(FieldsetField fieldsetField);

	boolean delete(FieldsetField fieldsetField);

	List<FieldsetField> selectByFieldsetKey(String fieldsetKey);
}
