package com.shineyue.htmldesign.service.impl;

import java.util.List;

import com.shineyue.htmldesign.extendmapper.PageComponentConfigExtend;
import com.shineyue.htmldesign.extendmapper.SqlmapExtendMapper;
import com.shineyue.htmldesign.mapper.PageComponentConfigMapper;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentConfig;
import com.shineyue.htmldesign.model.PageComponentConfigExample;
import com.shineyue.htmldesign.mybatis.MybatisUtils;
import com.shineyue.htmldesign.service.PageComponentConfigService;

public class PageComponentConfigServiceImpl implements
		PageComponentConfigService {

	public boolean savePageComponentConfig(PageComponentConfigExtend record) {
		int i = MybatisUtils.getSession().getMapper(SqlmapExtendMapper.class)
				.saveOrUpdatePageComponentConfig(record);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public List<PageComponentConfig> selectByPageComponet(
			PageComponent pageComponent) {
		PageComponentConfigExample example = new PageComponentConfigExample();

		if (pageComponent.getId() != null && pageComponent.getId() > 0) {
			example.createCriteria().andPageComponentIdEqualTo(
					pageComponent.getId());
		}
		return MybatisUtils.getSession()
				.getMapper(PageComponentConfigMapper.class)
				.selectByExample(example);
	}

}
