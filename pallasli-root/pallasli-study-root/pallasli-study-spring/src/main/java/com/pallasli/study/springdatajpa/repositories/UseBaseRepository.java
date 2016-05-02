package com.pallasli.study.springdatajpa.repositories;

import com.pallasli.study.springdatajpa.EmailAddress;
import com.pallasli.study.springdatajpa.User;

public interface UseBaseRepository extends MyBaseRepository<User, Long> {
	User findByEmailAddress(EmailAddress emailAddress);

}
