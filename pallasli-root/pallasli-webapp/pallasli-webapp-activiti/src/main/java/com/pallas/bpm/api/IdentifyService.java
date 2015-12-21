package com.pallas.bpm.api;

import com.pallas.bpm.bean.UserInfo;

public interface IdentifyService {

	boolean createUser(UserInfo userInfo);

	UserInfo findUser(long id);

	boolean saveUser(UserInfo userInfo);

	boolean deleteUser(UserInfo userInfo);

}
