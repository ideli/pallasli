package com.pallasli.authority.orgnization.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.authority.orgnization.AbstractDepartment;
import com.pallasli.authority.orgnization.AbstractOrgnization;
import com.pallasli.authority.orgnization.AbstractResource;
import com.pallasli.authority.orgnization.AbstractRole;
import com.pallasli.authority.orgnization.AbstractUser;

public class DepartmentManager {

	@Autowired
	public AbstractDepartment department;
	@Autowired
	public AbstractOrgnization orgnization;
	@Autowired
	public AbstractUser user;
	@Autowired
	public AbstractRole role;
	@Autowired
	public AbstractResource resource;

}
