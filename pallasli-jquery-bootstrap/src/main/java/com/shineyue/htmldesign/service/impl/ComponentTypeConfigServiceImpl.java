package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pallasli.sql.mybatis.MyBatisUtil;
import com.shineyue.htmldesign.mapper.ComponentTypeConfigMapper;
import com.shineyue.htmldesign.model.ComponentTypeConfig;
import com.shineyue.htmldesign.model.ComponentTypeConfigExample;
import com.shineyue.htmldesign.service.ComponentTypeConfigService;

@Transactional
public class ComponentTypeConfigServiceImpl implements
		ComponentTypeConfigService {

	@Override
	public List<ComponentTypeConfig> listConfigs() {
		ComponentTypeConfigExample example = new ComponentTypeConfigExample();

		return MyBatisUtil.getSession()
				.getMapper(ComponentTypeConfigMapper.class)
				.selectByExample(example);
	}

}
