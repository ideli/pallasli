package com.shineyue.htmldesign.contoller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.shineyue.htmldesign.bean.CompProperties;
import com.shineyue.htmldesign.extendmapper.PageComponentConfigExtend;
import com.shineyue.htmldesign.model.Page;
import com.shineyue.htmldesign.model.PageComponent;
import com.shineyue.htmldesign.model.PageComponentConfig;
import com.shineyue.htmldesign.service.PageComponentConfigService;
import com.shineyue.htmldesign.service.PageComponentService;
import com.shineyue.htmldesign.service.impl.PageComponentConfigServiceImpl;
import com.shineyue.htmldesign.service.impl.PageComponentServiceImpl;
import com.shineyue.utils.AjaxResult;

@Controller
@EnableAutoConfiguration
@RequestMapping(value = "/pagecomponent")
public class PageComponentController {
	PageComponentService pageComponentService = new PageComponentServiceImpl();
	PageComponentConfigService pageComponentConfigService = new PageComponentConfigServiceImpl();
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
	public AjaxResult loadPanels(PageComponent parent) {
		Page page = new Page();
		page.setId(parent.getPageId());
		AjaxResult result = new AjaxResult();
		panelTypeList.add(7);
		panelTypeList.add(8);
		panelTypeList.add(9);
		panelTypeList.add(10);
		List<PageComponent> ret = pageComponentService.listPageComponent(page,
				parent);
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

	@RequestMapping(value = "/savePageComponentConfig", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult savePageComponentConfig(
			@RequestParam(value = "pageCompConfigs[]") String[] pageCompConfigs,
			// Tmp tmp,
			@RequestParam(value = "datas[]") long[] datas,
			HttpServletRequest request) {
		// List<PageComponentConfigExtend> pageCompConfigs = tmp
		// .getPageCompConfigs();
		AjaxResult result = new AjaxResult();
		System.out.println("***********");
		// System.out.println(datas.length);
		System.out.println(pageCompConfigs.length);
		boolean ret = false;
		for (String confi : pageCompConfigs) {
			PageComponentConfigExtend cfg = new Gson().fromJson(confi,
					PageComponentConfigExtend.class);
			System.out.println(cfg.getConfigKey());
			System.out.println(cfg.getConfigValue());
			ret = pageComponentConfigService.savePageComponentConfig(cfg);
		}
		System.out.println("ret" + ret);
		result.setSuccess(true);
		return result;
	}

	@RequestMapping(value = "/loadPageComponentConfig", method = RequestMethod.GET)
	@ResponseBody
	public AjaxResult loadPageComponentConfig(PageComponent pageComp) {
		AjaxResult result = new AjaxResult();
		List<PageComponentConfig> ret = pageComponentConfigService
				.selectByPageComponet(pageComp);
		CompProperties data = new CompProperties();
		for (PageComponentConfig config : ret) {
			if ("height".equals(config.getConfigKey())) {
				if (!"".equals(config.getConfigValue())) {
					data.setHeight(Integer.parseInt(config.getConfigValue()));
				}
			}
			if ("width".equals(config.getConfigKey())) {
				if (!"".equals(config.getConfigValue())) {
					data.setWidth(Integer.parseInt(config.getConfigValue()));
				}
			}
		}
		result.setData(data);
		result.setSuccess(true);
		result.setTotalcount(ret.size());
		return result;
	}
}

class Tmp {
	private List<PageComponentConfigExtend> pageCompConfigs;

	public List<PageComponentConfigExtend> getPageCompConfigs() {
		return pageCompConfigs;
	}

	public void setPageCompConfigs(
			List<PageComponentConfigExtend> pageCompConfigs) {
		this.pageCompConfigs = pageCompConfigs;
	}
}