package com.pallasli.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pallasli.repository.UserRepository;
import com.pallasli.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	// 如果有多个类型匹配时，则会去匹配与这个属性名字相同的Bean的名字
	// @Repository("userRepository")
	// public class UserRepositoryImpl implements UserRepository{
	// 因为名字相同所以匹配成功
	// 可以将@Autowired放在字段或者方法上

	// 如果没有指定名字
	// 还有一种方法是使用@Qualifier("userRepositoryImpl")
	// 用来指定哪一个指定名字的Bean
	// 可以将该标签放在字段，设置方法或者设置方法的传入参数前面
	// @Autowired
	// @Qualifier("userRepositoryImpl")
	// public void setUserRepository(UserRepository userRepository) {
	// this.userRepository = userRepository;
	// }

	@Autowired
	public void setUserRepository(
			@Qualifier("userRepositoryImpl") UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void add() {
		System.out.println("UserService add...");
		userRepository.save();
	}
}
