package com.shineyue.htmldesign.service;

import java.util.List;

import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageComponent;

public interface PageComponentService {

	List<PageComponent> listPageComponent(Page page, PageComponent parent);

	boolean addPageComponent(PageComponent component);

	List<PageComponent> loadChildPageComponent(PageComponent parent);

	List<PageComponent> listAllPageFieldgroups();

}
