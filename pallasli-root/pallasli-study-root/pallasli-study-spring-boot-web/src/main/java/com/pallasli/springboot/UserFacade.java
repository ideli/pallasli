package com.pallasli.springboot;

import java.util.List;

public interface UserFacade {

	public List<User> selectAllUser();

	public User selectUserByUsername(String username);

	public int insertUser(User user);

	public int updateUserByUsername(User user);

	public int deleteUserByUsername(String username);

}
