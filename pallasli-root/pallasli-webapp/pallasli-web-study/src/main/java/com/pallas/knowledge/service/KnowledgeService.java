package com.pallas.knowledge.service;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.pallas.knowledge.bean.Knowledge;

public interface KnowledgeService {

	public JsonObject changeKnowledge(JsonArray dataarr);

	public List<Knowledge> loadKnowledges(long parentId);

	JsonObject addKnowledge(JsonArray dataarr);

	public JsonObject deleteKnowledge(JsonArray data);

}
