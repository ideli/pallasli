package com.shineyue.htmldesign.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shineyue.htmldesign.model.Module;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.service.PageComponentService;
import com.shineyue.htmldesign.service.impl.PageComponentServiceImpl;
import com.shineyue.utils.AjaxResult;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/pagecomponent")
public class PageComponentController {
	PageComponentService pageComponentService = new PageComponentServiceImpl();
	List<Integer> panelTypeList = new ArrayList<Integer>();

	@RequestMapping(value = "/loadChildPageComponent", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadChildPageComponent(PageComponent parent) {
		AjaxResult result = new AjaxResult();
		List<PageComponent> ret = pageComponentService
				.loadChildPageComponent(parent);
		result.setResults(ret);
		result.setSuccess(true);
		result.setTotalcount(ret.size());
		return result;
	}

	@RequestMapping(value = "/addPageComponent", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addPageComponent(PageComponent pc) {
		AjaxResult result = new AjaxResult();
		boolean ret = pageComponentService.addPageComponent(pc);
		System.out.println("ret" + ret);
		result.setSuccess(true);
		return result;
	}

	@RequestMapping(value = "/loadPanels", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadPanels(Module module, PageComponent parent) {
		AjaxResult result = new AjaxResult();
		panelTypeList.add(7);
		panelTypeList.add(8);
		panelTypeList.add(9);
		panelTypeList.add(10);
		List<PageComponent> ret = pageComponentService.listPageComponent(
				module, parent);
		result.setResults(ret);
		result.setSuccess(true);
		result.setTotalcount(ret.size());
		return result;
	}

	@RequestMapping(value = "/addPanel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult addPanel(PageComponent panel) {
		AjaxResult result = new AjaxResult();
		boolean ret = pageComponentService.addPageComponent(panel);
		System.out.println("ret" + ret);
		result.setSuccess(true);
		return result;
	}
}
