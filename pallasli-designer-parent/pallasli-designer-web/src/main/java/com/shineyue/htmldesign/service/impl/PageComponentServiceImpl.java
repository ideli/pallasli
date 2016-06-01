package com.shineyue.htmldesign.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shineyue.htmldesign.mapper.PageComponentMapper;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentExample;
import com.shineyue.htmldesign.mybatis.MybatisUtils;
import com.shineyue.htmldesign.service.PageComponentService;

@Transactional
public class PageComponentServiceImpl implements PageComponentService {

	public List<PageComponent> listPageComponent(Page page, PageComponent parent) {
		PageComponentExample example = new PageComponentExample();
		if (parent != null && parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andPageIdEqualTo(page.getId())
					.andParentIdEqualTo(parent.getId()).andPageIdIsNotNull();// .andPageIdEqualTo(module.getId());
		} else {
			example.createCriteria().andPageIdEqualTo(page.getId())
					.andParentIdEqualTo(0).andPageIdIsNotNull();
			// .andPageIdEqualTo(module.getId());
		}

		return MybatisUtils.getSession().getMapper(PageComponentMapper.class)
				.selectByExample(example);
	}

	public boolean addPageComponent(PageComponent pageComp) {
		int i = MybatisUtils.getSession().getMapper(PageComponentMapper.class)
				.insert(pageComp);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public List<PageComponent> loadChildPageComponent(PageComponent parent) {
		PageComponentExample example = new PageComponentExample();
		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andParentIdEqualTo(parent.getId())
					.andPageIdEqualTo(parent.getPageId());
			// .andPageIdEqualTo(module.getId());
		} else {
			example.createCriteria().andPageIdEqualTo(parent.getPageId());
			// .andPageIdEqualTo(module.getId());
		}

		return MybatisUtils.getSession().getMapper(PageComponentMapper.class)
				.selectByExample(example);
	}

	public List<PageComponent> listAllPageFieldgroups() {
		PageComponentExample example = new PageComponentExample();
		List<Integer> values = new ArrayList<Integer>();
		values.add(7);
		values.add(8);
		values.add(9);
		values.add(10);
		example.createCriteria().andComponentTypeIdIn(values);

		return MybatisUtils.getSession().getMapper(PageComponentMapper.class)
				.selectByExample(example);
	}

}
