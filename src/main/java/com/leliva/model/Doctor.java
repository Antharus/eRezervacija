package com.leliva.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import com.leliva.model.helpers.DoctorOccupation;
import com.leliva.model.helpers.DoctorWorkingState;

@Entity
public class Doctor implements IEntity<Long> {

	@Id
	@SequenceGenerator(name = "GEN_DOCTOR", sequenceName = "GEN_DOCTOR")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_DOCTOR")
	private Long id;

	@Column(length = 124)
	private String name;

	@Column(length = 124)
	private String surname;

	@Enumerated(EnumType.STRING)
	private DoctorOccupation doctorOccupation;

	@Enumerated(EnumType.STRING)
	private DoctorWorkingState state;

	public DoctorWorkingState getState() {
		return state;
	}

	public void setState(DoctorWorkingState state) {
		this.state = state;
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

	public DoctorOccupation getDoctorOccupation() {
		return doctorOccupation;
	}

	public void setDoctorOccupation(DoctorOccupation doctorOccupation) {
		this.doctorOccupation = doctorOccupation;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Version
	private Integer version;

	@Override
	public Integer getVersion() {
		return version;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;

	}

	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", surname=" + surname + ", doctorOccupation=" + doctorOccupation
				+ ", state=" + state + ", version=" + version + "]";
	}

}
