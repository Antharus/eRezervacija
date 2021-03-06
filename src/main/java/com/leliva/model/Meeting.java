package com.leliva.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import org.hibernate.annotations.Type;

@Entity
public class Meeting implements IEntity<Long> {

	@Id
	@SequenceGenerator(name = "GEN_MEETING", sequenceName = "GEN_MEETING")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_MEETING")
	private Long id;

	private Date meetingDate;

	@Version
	private Integer version;

	@OneToOne(cascade = CascadeType.ALL)
	private Person person;

	@OneToOne
	private Doctor doctor;
	
	@Column(name="canceled", nullable = true) 
	private boolean canceled;

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getMeetingDate() {
		return meetingDate;
	}

	public void setMeetingDate(Date meetingDate) {
		this.meetingDate = meetingDate;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Integer getVersion() {
		return version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Meeting [id=" + id + ", meetingDate=" + meetingDate + ", version=" + version + ", person=" + person
				+ ", doctor=" + doctor + "]";
	}
	
	

}
