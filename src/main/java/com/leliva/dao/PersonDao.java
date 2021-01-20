package com.leliva.dao;

import com.leliva.model.Person;

public interface PersonDao {
	
	Person savePerson(Person p);
	
	Person getPersonByInfo(String name, String surname, String code);

}
