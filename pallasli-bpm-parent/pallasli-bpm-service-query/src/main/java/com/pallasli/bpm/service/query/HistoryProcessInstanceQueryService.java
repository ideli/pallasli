package com.pallasli.bpm.service.query;

public interface HistoryProcessInstanceQueryService {

	void listProInstByStartUser(String user);

	void listProInstByCandidateUser(String user);

	void listProInstByTerminateUser(String user);

}
