package com.shineyue.htmldesign.mapper;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shineyue.htmldesign.model.User;

@Repository
@Transactional
public interface UserMapper {

	User getUserById(long id);

	void create(String sql);
}
