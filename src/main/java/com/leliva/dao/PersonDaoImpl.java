package com.leliva.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.leliva.model.Person;

@Service
public class PersonDaoImpl extends AbstractGenericDao<Person, Long> implements PersonDao {
	
	private static final String SELECT_PERSON_BY_PERSON_INFO = "SELECT p FROM Person p WHERE p.name=:name AND p.surname=:surname AND p.personCode=:personCode";

	@Override
	public Person savePerson(Person p) {
		return save(p);
	}

	@Override
	public Person getPersonByInfo(String name, String surname, String code) {
		Query query = getEntityManager().createQuery(SELECT_PERSON_BY_PERSON_INFO);
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		query.setParameter("personCode", code);
		Person person = getSingleResultOrNull(query);
		return person;
	}

}
