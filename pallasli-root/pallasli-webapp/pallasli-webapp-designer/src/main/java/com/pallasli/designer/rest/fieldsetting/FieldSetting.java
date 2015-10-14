package com.pallasli.designer.rest.fieldsetting;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pallas.design.bean.Table;
import com.pallas.design.service.TableService;
import com.pallasli.constant.SystemConstant;

@Controller(value = "fieldSetting")
@RequestMapping("/fieldsetting")
class FieldSetting {
	private final TableService tableService;

	public TableService getWordService() {
		return tableService;
	}

	public FieldSetting() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("/"
				+ SystemConstant.WEB_ROOT + "WEB-INF/application-context.xml");
		tableService = (TableService) ctx.getBean("tableService");
	}

	@RequestMapping(value = "/listtables", method = RequestMethod.GET)
	@ResponseBody
	List<Table> listTables() {
		List<Table> list = tableService.loadTables();
		return list;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Table get(@PathVariable long id) {
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	Table update(@PathVariable long id, @RequestParam Table updated) {
		return updated;
	}
}
