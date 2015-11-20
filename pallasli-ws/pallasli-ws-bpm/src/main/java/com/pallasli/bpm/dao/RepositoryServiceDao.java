package com.pallasli.bpm.dao;

import com.pallasli.bpm.bean.ModelInfo;
import com.pallasli.bpm.bean.NodeInfo;

public interface RepositoryServiceDao {

	public String createProcessDefinition(String key, String name,
			String category, String description);

	public String saveProcessDefinition(String modelId, String key,
			String name, String category, String description,
			NodeInfo editorNode);

	public boolean deleteProcessModel(String modelId);

	public String copyProcessModel(String name, String description,
			String modelId);

	public boolean importProcessModelInfo(ModelInfo modelInfo,
			boolean isOverride);

	public ModelInfo exportProcessModelInfo(String modelId);

	public boolean unDeployProcessModel(String modelId);

	public ModelInfo getProcessDefinition(String modelId);

	public byte[] getEditorSource(String modelId);
}
