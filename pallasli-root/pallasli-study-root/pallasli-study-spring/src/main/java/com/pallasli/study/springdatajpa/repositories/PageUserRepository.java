package com.pallasli.study.springdatajpa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.pallasli.study.springdatajpa.User;

public interface PageUserRepository extends CrudRepository<User, Long> {

	User findFirstByOrderByLastnameAsc();

	User findTopByOrderByAgeDesc();

	Page<User> queryFirst10ByLastname(String lastname, Pageable pageable);

	Slice<User> findTop3ByLastname(String lastname, Pageable pageable);

	List<User> findFirst10ByLastname(String lastname, Sort sort);

	List<User> findTop10ByLastname(String lastname, Pageable pageable);
}
