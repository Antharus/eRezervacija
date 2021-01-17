package com.leliva.dao;

import org.springframework.stereotype.Service;

import com.leliva.model.Person;

@Service
public class PersonDaoImpl extends GenericDao<Person, Long> implements PersonDao {

	@Override
	public Person savePerson(Person p) {
		return save(p);
	}

}
