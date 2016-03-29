package com.pallasli.bpm.service;

public interface HistoryProcessInstanceQueryService {

	void listProInstByStartUser(String user);

	void listProInstByCandidateUser(String user);

	void listProInstByTerminateUser(String user);

}
