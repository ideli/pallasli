package com.pallasli.study.springdatajpa.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.pallasli.study.springdatajpa.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Long countByLastname(String lastname);

	Long deleteByLastname(String lastname);

	List<User> removeByLastname(String lastname);
}
