package com.pallasli.webapp.edu.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallasli.webapp.edu.mapper.UserInfoMapper;
import com.pallasli.webapp.edu.mapper.UserLoginLogMapper;
import com.pallasli.webapp.edu.mapper.UserRegisterMapper;
import com.pallasli.webapp.edu.model.UserLoginLog;
import com.pallasli.webapp.edu.model.UserRegister;
import com.pallasli.webapp.edu.model.UserRegisterExample;
import com.pallasli.webapp.edu.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRegisterMapper userRegisterMapper;
	@Autowired
	private UserInfoMapper userInfoMapper;
	@Autowired
	private UserLoginLogMapper userLoginLogMapper;

	@Override
	public UserRegister login(UserRegister userRegister) {
		UserRegisterExample example = new UserRegisterExample();
		example.createCriteria().andUserNameEqualTo(userRegister.getUserName())
				.andPasswordEqualTo(userRegister.getPassword());
		List<UserRegister> list = userRegisterMapper.selectByExample(example);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean register(UserRegister userRegister) {
		String uuid = UUID.randomUUID().toString();
		userRegister.setId(uuid);
		int ret = userRegisterMapper.insert(userRegister);
		boolean flag = ret > 0;
		return flag;
	}

	@Override
	public boolean addLoginLog(UserLoginLog userLoginLog) {
		String uuid = UUID.randomUUID().toString();
		userLoginLog.setId(uuid);
		int ret = userLoginLogMapper.insert(userLoginLog);
		boolean flag = ret > 0;
		return flag;
	}

}
