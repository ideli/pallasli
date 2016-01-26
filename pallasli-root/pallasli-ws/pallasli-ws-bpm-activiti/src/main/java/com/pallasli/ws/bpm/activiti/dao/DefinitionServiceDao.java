package com.pallasli.ws.bpm.activiti.dao;

import java.util.List;

import com.pallasli.bpm.api.bean.ModelInfo;
import com.pallasli.bpm.api.bean.NodeInfo;

public interface DefinitionServiceDao {

	public List<NodeInfo> getNextNodeList(String user, String processKey,
			String processInstanceId);

	public List<ModelInfo> findProcessModelInfo(String keyword,
			int firstResult, int pageSize);

}
