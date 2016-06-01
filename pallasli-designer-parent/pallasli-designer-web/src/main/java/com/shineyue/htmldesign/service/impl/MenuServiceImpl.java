package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shineyue.htmldesign.mapper.ModuleMapper;
import com.shineyue.htmldesign.mapper.PageMapper;
import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.ModuleExample;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageExample;
import com.shineyue.htmldesign.mybatis.MybatisUtils;
import com.shineyue.htmldesign.service.MenuService;

@Transactional
public class MenuServiceImpl implements MenuService {

	public List<Module> listModules(Module parent) {
		ModuleExample example = new ModuleExample();
		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andParentIdEqualTo(parent.getId());
		} else {
			example.createCriteria().andParentIdIsNull();
			example.or().andParentIdEqualTo(0);

		}
		example.setOrderByClause("order_num asc");
		return MybatisUtils.getSession().getMapper(ModuleMapper.class)
				.selectByExample(example);
	}

	public List<Page> listPages(Module parent) {
		PageExample example = new PageExample();

		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andModuleIdEqualTo(parent.getId());
		}
		example.setOrderByClause("order_num asc");
		return MybatisUtils.getSession().getMapper(PageMapper.class)
				.selectByExample(example);
	}

	public boolean addModule(Module record) {
		int i = MybatisUtils.getSession().getMapper(ModuleMapper.class)
				.insert(record);
		System.out.println(record.getCaption());
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public boolean addPage(Page record) {
		int i = MybatisUtils.getSession().getMapper(PageMapper.class)
				.insert(record);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public boolean alterModule(Module record) {
		int i = MybatisUtils.getSession().getMapper(ModuleMapper.class)
				.updateByPrimaryKey(record);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public boolean alterPage(Page record) {
		int i = MybatisUtils.getSession().getMapper(PageMapper.class)
				.updateByPrimaryKey(record);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public boolean delModule(int id) {
		int i = MybatisUtils.getSession().getMapper(ModuleMapper.class)
				.deleteByPrimaryKey(id);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public boolean delPage(int id) {
		int i = MybatisUtils.getSession().getMapper(PageMapper.class)
				.deleteByPrimaryKey(id);
		MybatisUtils.getSession().commit();
		return i > 0;
	}

	public List<Module> listAllModules() {
		ModuleExample example = new ModuleExample();
		example.setOrderByClause("order_num asc");
		return MybatisUtils.getSession().getMapper(ModuleMapper.class)
				.selectByExample(example);
	}

	public List<Page> listAllPages() {
		PageExample example = new PageExample();
		example.setOrderByClause("order_num asc");
		return MybatisUtils.getSession().getMapper(PageMapper.class)
				.selectByExample(example);
	}

}
