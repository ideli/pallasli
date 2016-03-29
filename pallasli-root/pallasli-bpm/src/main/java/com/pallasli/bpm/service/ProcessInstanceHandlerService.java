package com.pallasli.bpm.service;

import org.activiti.engine.runtime.ProcessInstance;

public interface ProcessInstanceHandlerService {

	public void cancel();

	public void delete();

	public void foreTerminate();

	public ProcessInstance startByProKey(String proKey);
}
