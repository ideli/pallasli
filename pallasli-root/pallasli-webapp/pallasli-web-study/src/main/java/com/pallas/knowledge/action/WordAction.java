package com.pallas.knowledge.action;

import java.util.List;

import org.springframework.context.ApplicationContext;

import com.google.gson.JsonArray;
import com.pallas.action.BaseAction;
import com.pallas.knowledge.bean.TreeNode;
import com.pallas.knowledge.service.WordService;
import com.softwarementors.extjs.djn.config.annotations.DirectMethod;

public class WordAction extends BaseAction {

	private final WordService wordService;

	public WordService getWordService() {
		return wordService;
	}

	public WordAction() {
		ApplicationContext ctx = super.getContext();
		wordService = (WordService) ctx.getBean("wordService");
	}

	@DirectMethod(method = "loadWordType")
	public List<TreeNode> loadWordType(JsonArray data) {
		return wordService.loadWordType(data);
	}

	@DirectMethod(method = "addWordType")
	public List<TreeNode> addWordType(JsonArray data) {
		return wordService.addWordType(data);
	}

	@DirectMethod(method = "alterWordType")
	public List<TreeNode> alterWordType(JsonArray data) {
		return wordService.alterWordType(data);
	}

}
