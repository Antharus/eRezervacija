package com.leliva.dao;

import com.leliva.model.Meeting;

public interface MeetingDao {

	Meeting saveMeeting(Meeting meeting);
	
	Meeting getMeetingByPersonCode(Integer code);
	
}
