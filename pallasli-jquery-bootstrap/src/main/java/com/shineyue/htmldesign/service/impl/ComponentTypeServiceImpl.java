package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pallasli.sql.mybatis.MyBatisUtil;
import com.shineyue.htmldesign.mapper.ComponentTypeMapper;
import com.shineyue.htmldesign.model.ComponentType;
import com.shineyue.htmldesign.model.ComponentTypeExample;
import com.shineyue.htmldesign.service.ComponentTypeService;

@Transactional
public class ComponentTypeServiceImpl implements ComponentTypeService {

	@Override
	public List<ComponentType> listCompTypes() {
		ComponentTypeExample example = new ComponentTypeExample();
		return MyBatisUtil.getSession().getMapper(ComponentTypeMapper.class)
				.selectByExample(example);
	}

}
