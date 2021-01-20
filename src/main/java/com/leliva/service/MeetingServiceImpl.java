package com.leliva.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leliva.dao.MeetingDao;
import com.leliva.model.Doctor;
import com.leliva.model.Meeting;
import com.leliva.model.Person;

@Service(value = "MeetingService")
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	MeetingDao meetingDao;

	public Meeting saveMeeting(Meeting meeting) {
		return meetingDao.saveMeeting(meeting);
	}

	@Override
	public List<Meeting> getMeetingsByPersonInfo(String name, String surname, String code) {
		return meetingDao.getMeetingByPersonInfo(name, surname, code);
	}

	@Override
	public List<Meeting> getMeetingsByPersonInfo(Person p) {
		return getMeetingsByPersonInfo(p.getName(), p.getSurname(), p.getPersonCode());
	}

	@Override
	public Meeting getMeeting(String name, String surname, String code, Doctor doc, Date startDate, Date endDate) {
		return meetingDao.getMeeting(name, surname, code, doc, startDate, endDate);
	}

	@Override
	public Meeting getMeeting(Person p, Doctor doc, Date startDate, Date endDate) {
		return getMeeting(p.getName(), p.getSurname(), p.getPersonCode(), doc, startDate, endDate);
	}

	@Override
	public List<Meeting> getMeetingsByDoctor(Long docId, Date date) {
		return meetingDao.getMeetingsByDoctor(docId, date);
	}

	@Override
	public void cancelMeeting(Long meetingId, Long doctorId) {
		meetingDao.cancelMeeting(meetingId);
	}

	@Override
	public List<Date> getAvailableTimesByDoctor(Long doctorId, Date date) {
		return meetingDao.getAvailableTimesByDoctor(doctorId, date);
	}

}
