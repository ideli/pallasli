package com.pallas.design.dao;

import java.util.List;

import com.pallas.design.bean.Fieldset;

public interface FieldsetDAO {
	List<Fieldset> selectAll();

	Fieldset insert(Fieldset fieldset);

	boolean update(Fieldset fieldset);

	boolean delete(Fieldset fieldset);
}
