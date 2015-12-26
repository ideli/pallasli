package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pallasli.sql.mybatis.MyBatisUtil;
import com.shineyue.htmldesign.mapper.PageComponentMapper;
import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentExample;
import com.shineyue.htmldesign.service.PageComponentService;

@Transactional
public class PageComponentServiceImpl implements PageComponentService {

	@Override
	public List<PageComponent> listPageComponent(Module module,
			PageComponent parent) {
		PageComponentExample example = new PageComponentExample();
		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andParentIdEqualTo(parent.getId())
					.andPageIdIsNotNull();// .andPageIdEqualTo(module.getId());
		} else {
			example.createCriteria().andParentIdEqualTo(0).andPageIdIsNotNull();
			// .andPageIdEqualTo(module.getId());
		}

		return MyBatisUtil.getSession().getMapper(PageComponentMapper.class)
				.selectByExample(example);
	}

	@Override
	public boolean addPageComponent(PageComponent panel) {
		int i = MyBatisUtil.getSession().getMapper(PageComponentMapper.class)
				.insert(panel);
		return i > 0;
	}

	@Override
	public List<PageComponent> loadChildPageComponent(PageComponent parent) {
		PageComponentExample example = new PageComponentExample();
		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andParentIdEqualTo(parent.getId())
					.andPageIdIsNotNull();// .andPageIdEqualTo(module.getId());
		} else {
			example.createCriteria().andParentIdEqualTo(0).andPageIdIsNotNull();
			// .andPageIdEqualTo(module.getId());
		}

		return MyBatisUtil.getSession().getMapper(PageComponentMapper.class)
				.selectByExample(example);
	}

}
