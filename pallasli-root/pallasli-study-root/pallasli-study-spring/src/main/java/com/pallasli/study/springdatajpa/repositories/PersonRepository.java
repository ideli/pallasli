package com.pallasli.study.springdatajpa.repositories;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.pallasli.study.springdatajpa.EmailAddress;
import com.pallasli.study.springdatajpa.Person;
import com.pallasli.study.springdatajpa.User;

public interface PersonRepository extends Repository<User, Long> {
	List<Person> findByLastname(String lastname);

	List<Person> findByEmailAddressAndLastname(EmailAddress emailAddress,
			String lastname);

	// Enables the distinct flag for the query
	List<Person> findDistinctPeopleByLastnameOrFirstname(String lastname,
			String firstname);

	List<Person> findPeopleDistinctByLastnameOrFirstname(String lastname,
			String firstname);

	// Enabling ignoring case for an individual property
	List<Person> findByLastnameIgnoreCase(String lastname);

	// Enabling ignoring case for all suitable properties
	List<Person> findByLastnameAndFirstnameAllIgnoreCase(String lastname,
			String firstname);

	// Enabling static ORDER BY for a query
	List<Person> findByLastnameOrderByFirstnameAsc(String lastname);

	List<Person> findByLastnameOrderByFirstnameDesc(String lastname);
}