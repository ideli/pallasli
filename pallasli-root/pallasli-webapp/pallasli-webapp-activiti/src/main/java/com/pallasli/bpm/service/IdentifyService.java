package com.pallasli.bpm.service;

import com.pallasli.bpm.service.bean.UserInfo;

public interface IdentifyService {

	boolean createUser(UserInfo userInfo);

	UserInfo findUser(long id);

	boolean saveUser(UserInfo userInfo);

	boolean deleteUser(UserInfo userInfo);

}
