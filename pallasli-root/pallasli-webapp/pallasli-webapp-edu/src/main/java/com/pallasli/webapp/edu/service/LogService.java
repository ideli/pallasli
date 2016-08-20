package com.pallasli.webapp.edu.service;

import java.util.List;

import com.pallasli.webapp.edu.model.UserLoginLog;

public interface LogService {

	public boolean listOperationLog();

	public List<UserLoginLog> listLoginLog(String userId);
}
