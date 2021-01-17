package com.leliva.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leliva.dao.MeetingDao;
import com.leliva.dao.PersonDao;
import com.leliva.model.Meeting;

@Service(value = "MeetingService")
public class MeetingServiceImpl implements MeetingService {

	@Autowired
	MeetingDao meetingDao;
	
	@Autowired
	PersonDao personDao;

	public Meeting saveMeeting(Meeting meeting) {
//		personDao.savePeerson(meeting.getPerson());
		return meetingDao.saveMeeting(meeting);
	}

	public Meeting getMeetingByPersonCode(Integer code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Meeting getMeetingByPersonInfo(String name, String surname, Integer code) {
		// TODO Auto-generated method stub
		return null;
	}

}
