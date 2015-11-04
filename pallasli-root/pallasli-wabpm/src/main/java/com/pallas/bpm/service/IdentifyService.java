package com.pallas.bpm.service;

import com.pallas.bpm.service.bean.UserInfo;

public interface IdentifyService {

	boolean createUser(UserInfo userInfo);

	UserInfo findUser(long id);

	boolean saveUser(UserInfo userInfo);

	boolean deleteUser(UserInfo userInfo);

}
