package com.shineyue.htmldesign.extendmapper;

import java.util.Map;

public interface SqlmapExtendMapper {
	int saveOrUpdatePageComponentConfig(PageComponentConfigExtend record);

	Integer getMaxId(Map<String, Object> params);

}