package com.pallasli.bpm.service.query;

public interface TaskHandlerService {

	void complete(String taskId);

	public void complete(String taskId, String nextUser);
}
