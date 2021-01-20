package com.leliva.service;

import com.leliva.model.Person;

public interface PersonService {
	
	Person getPersonByInfo(String name, String surname, String code);

}
