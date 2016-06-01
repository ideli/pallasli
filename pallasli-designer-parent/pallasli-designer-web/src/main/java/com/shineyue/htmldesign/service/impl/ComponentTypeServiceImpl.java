package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shineyue.htmldesign.mapper.ComponentTypeMapper;
import com.shineyue.htmldesign.model.ComponentType;
import com.shineyue.htmldesign.model.ComponentTypeExample;
import com.shineyue.htmldesign.mybatis.MybatisUtils;
import com.shineyue.htmldesign.service.ComponentTypeService;

@Transactional
public class ComponentTypeServiceImpl implements ComponentTypeService {

	public List<ComponentType> listCompTypes() {
		ComponentTypeExample example = new ComponentTypeExample();
		return MybatisUtils.getSession().getMapper(ComponentTypeMapper.class)
				.selectByExample(example);
	}

}
