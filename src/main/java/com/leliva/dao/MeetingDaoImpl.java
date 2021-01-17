package com.leliva.dao;

import org.springframework.stereotype.Service;

import com.leliva.model.Meeting;

@Service
public class MeetingDaoImpl extends GenericDao<Meeting, Long> implements MeetingDao {

	public Meeting saveMeeting(Meeting meeting) {
		return save(meeting);
	}

	public Meeting getMeetingByPersonCode(Integer code) {
		// TODO Auto-generated method stub
		return null;
	}

}
