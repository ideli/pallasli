package com.pallasli.bpm.service.impl;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallasli.bpm.service.BpmModelManagerService;
import com.pallasli.bpm.service.bean.EditorModel;
import com.pallasli.bpm.service.bean.ModelInfo;
import com.pallasli.bpm.service.bean.ProcessModelFile;

public class BpmModelManagerServiceImpl implements BpmModelManagerService {

	@Override
	public String createModel(JsonObject params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveModel(EditorModel editorModel) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteModel(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String copyModel(String fromModelId, JsonObject newParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateModel(byte[] processFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<ModelInfo> findModelList(JsonObject params, int pageSize,
			int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deployModel(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unDeployModelByModelId(String modelId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean unDeployModelByDeploymentId(String deploymentId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean importModel(ProcessModelFile processFile) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProcessModelFile exportModel(String modelId) {
		// TODO Auto-generated method stub
		return null;
	}

}
