package com.pallasli.bpm.service;

public interface TaskHandlerService {

	void complete(String taskId);

	public void complete(String taskId, String nextUser);
}
