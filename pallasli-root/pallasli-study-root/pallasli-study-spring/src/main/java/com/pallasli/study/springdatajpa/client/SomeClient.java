package com.pallasli.study.springdatajpa.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.pallasli.study.springdatajpa.Person;
import com.pallasli.study.springdatajpa.repositories.PersonRepository;

public class SomeClient {
	@Autowired
	private PersonRepository repository;

	public void doSomething() {
		List<Person> persons = repository.findByLastname("Matthews");
	}

}
