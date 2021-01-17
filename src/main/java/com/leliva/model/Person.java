package com.leliva.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

@Entity
public class Person implements IEntity<Long> {
	
	@Id
	@SequenceGenerator(name = "GEN_PERSON", sequenceName = "GEN_PERSON")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_PERSON")
	private Long id;

	@Column(length = 124)
	private String name;

	@Column(length = 124)
	private String surname;

	@Column(length = 11)
	private Integer personCode;
	
	@Version
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getPersonCode() {
		return personCode;
	}

	public void setPersonCode(Integer personCode) {
		this.personCode = personCode;
	}

	@Override
	public Integer getVersion() {
		return version;
	}

}
