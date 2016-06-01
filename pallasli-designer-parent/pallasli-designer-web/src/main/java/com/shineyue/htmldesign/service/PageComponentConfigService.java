package com.shineyue.htmldesign.service;

import java.util.List;

import com.shineyue.htmldesign.extendmapper.PageComponentConfigExtend;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentConfig;

public interface PageComponentConfigService {
	public boolean savePageComponentConfig(
			PageComponentConfigExtend pageComponentConfig);

	public List<PageComponentConfig> selectByPageComponet(
			PageComponent pageComponent);
}
