package com.pallasli.webapp.edu.service;

import com.pallasli.webapp.edu.model.UserLoginLog;
import com.pallasli.webapp.edu.model.UserRegister;

public interface LoginService {
	public UserRegister login(UserRegister userRegister);

	public boolean register(UserRegister userRegister);

	public boolean addLoginLog(UserLoginLog userLoginLog);
}
