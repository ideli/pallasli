package com.pallas.bpm.service;

public interface BpmInstanceManagerService {
	void deleteProcess();

	void suspendProcess();

	void activiteProcess();

	void findLogs();

	void findTasks();

	void findJobs();

	void getVariables();

	void getActivities();
}
