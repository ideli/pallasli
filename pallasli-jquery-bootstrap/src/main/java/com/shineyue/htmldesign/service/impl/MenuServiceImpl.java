package com.shineyue.htmldesign.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.pallasli.sql.mybatis.MyBatisUtil;
import com.shineyue.htmldesign.mapper.ModuleMapper;
import com.shineyue.htmldesign.mapper.PageMapper;
import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.ModuleExample;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageExample;
import com.shineyue.htmldesign.service.MenuService;

@Transactional
public class MenuServiceImpl implements MenuService {

	@Override
	public List<Module> listModule(Module parent) {
		ModuleExample example = new ModuleExample();
		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andParentIdEqualTo(parent.getId());
		} else {
			example.createCriteria().andParentIdEqualTo(0);
		}

		return MyBatisUtil.getSession().getMapper(ModuleMapper.class)
				.selectByExample(example);
	}

	@Override
	public List<Page> listPage(Module parent) {
		PageExample example = new PageExample();

		if (parent.getId() != null && parent.getId() > 0) {
			example.createCriteria().andModuleIdEqualTo(parent.getId());
		}
		return MyBatisUtil.getSession().getMapper(PageMapper.class)
				.selectByExample(example);
	}

	@Override
	public boolean addModule(Module record) {
		int i = MyBatisUtil.getSession().getMapper(ModuleMapper.class)
				.insert(record);
		return i > 0;
	}

	@Override
	public boolean addPage(Page record) {
		int i = MyBatisUtil.getSession().getMapper(PageMapper.class)
				.insert(record);
		return i > 0;
	}

	@Override
	public boolean alterModule(Module record) {
		int i = MyBatisUtil.getSession().getMapper(ModuleMapper.class)
				.updateByPrimaryKey(record);
		return i > 0;
	}

	@Override
	public boolean alterPage(Page record) {
		int i = MyBatisUtil.getSession().getMapper(PageMapper.class)
				.updateByPrimaryKey(record);
		return i > 0;
	}

	@Override
	public boolean delModule(int id) {
		int i = MyBatisUtil.getSession().getMapper(ModuleMapper.class)
				.deleteByPrimaryKey(id);
		return i > 0;
	}

	@Override
	public boolean delPage(int id) {
		int i = MyBatisUtil.getSession().getMapper(PageMapper.class)
				.deleteByPrimaryKey(id);
		return i > 0;
	}

}
