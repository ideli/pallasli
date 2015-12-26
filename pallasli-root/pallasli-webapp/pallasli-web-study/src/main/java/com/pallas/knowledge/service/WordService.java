package com.pallas.knowledge.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.pallas.knowledge.bean.TreeNode;

public interface WordService {

	List<TreeNode> alterWordType(JsonArray data);

	List<TreeNode> addWordType(JsonArray data);

	List<TreeNode> loadWordType(JsonArray data);

}
