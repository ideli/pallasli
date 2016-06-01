package com.shineyue.htmldesign.mapper;

import com.shineyue.htmldesign.model.Entity;

public interface EntityMapper<T extends Entity> {

	int deleteByPrimaryKey(T id);

	int insert(T record);

	int insertSelective(T record);

	T selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(T record);

	int updateByPrimaryKey(T record);
}
