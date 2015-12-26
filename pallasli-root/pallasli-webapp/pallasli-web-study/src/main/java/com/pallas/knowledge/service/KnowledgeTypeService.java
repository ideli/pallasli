package com.pallas.knowledge.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.knowledge.bean.KnowledgeType;

public interface KnowledgeTypeService {

	public List<KnowledgeType> loadKnowledgeType(KnowledgeType data);

	public List<KnowledgeType> addKnowledgeType(KnowledgeType data);

	public List<KnowledgeType> alterKnowledgeType(KnowledgeType data);

	public JsonObject deleteKnowledgeType(KnowledgeType data);

}
