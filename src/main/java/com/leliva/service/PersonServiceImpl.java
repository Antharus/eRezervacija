package com.leliva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leliva.dao.PersonDao;
import com.leliva.model.Person;

@Service
public class PersonServiceImpl implements PersonService{
	
	@Autowired
	PersonDao personDao;

	@Override
	public Person getPersonByInfo(String name, String surname, String code) {
		return personDao.getPersonByInfo(name, surname, code);
	}

}
