package com.shineyue.htmldesign.service;

import java.util.List;

import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.PageComponent;

public interface PageComponentService {

	List<PageComponent> listPageComponent(Module module, PageComponent parent);

	boolean addPageComponent(PageComponent panel);

	List<PageComponent> loadChildPageComponent(PageComponent parent);

}
