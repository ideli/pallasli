package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shineyue.htmldesign.mapper.ComponentTypeConfigMapper;
import com.shineyue.htmldesign.model.ComponentTypeConfig;
import com.shineyue.htmldesign.model.ComponentTypeConfigExample;
import com.shineyue.htmldesign.mybatis.MybatisUtils;
import com.shineyue.htmldesign.service.ComponentTypeConfigService;

@Transactional
public class ComponentTypeConfigServiceImpl implements
		ComponentTypeConfigService {

	public List<ComponentTypeConfig> listConfigs() {
		ComponentTypeConfigExample example = new ComponentTypeConfigExample();

		return MybatisUtils.getSession()
				.getMapper(ComponentTypeConfigMapper.class)
				.selectByExample(example);
	}

}
