package com.pallasli.webapp.edu.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pallasli.webapp.edu.mapper.UserLoginLogMapper;
import com.pallasli.webapp.edu.model.UserLoginLog;
import com.pallasli.webapp.edu.model.UserLoginLogExample;
import com.pallasli.webapp.edu.service.LogService;

@Service("logService")
public class LogServiceImpl implements LogService {

	@Autowired
	private UserLoginLogMapper userLoginLogMapper;

	@Override
	public List<UserLoginLog> listLoginLog(String userId) {

		UserLoginLogExample example = new UserLoginLogExample();
		example.createCriteria().andLoginIdEqualTo(userId);
		List<UserLoginLog> list = userLoginLogMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean listOperationLog() {

		return false;
	}

}
