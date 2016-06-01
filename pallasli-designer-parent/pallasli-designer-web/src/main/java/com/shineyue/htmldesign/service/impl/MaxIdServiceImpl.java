package com.shineyue.htmldesign.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.shineyue.htmldesign.extendmapper.SqlmapExtendMapper;
import com.shineyue.htmldesign.mybatis.MybatisUtils;
import com.shineyue.htmldesign.service.MaxIdService;

public class MaxIdServiceImpl implements MaxIdService {

	public int getMaxId(String tableName) {
		Map<String, Object> params = new HashMap<String, Object>();
		// 以name字段升序排序，
		params.put("tableName", tableName);
		Integer i = MybatisUtils.getSession()
				.getMapper(SqlmapExtendMapper.class).getMaxId(params);
		if (i == null)
			i = 0;
		return i;
	}

}
