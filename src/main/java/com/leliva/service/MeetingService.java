package com.leliva.service;

import com.leliva.model.Meeting;

public interface MeetingService {
	
	Meeting saveMeeting(Meeting meeting);
	
	Meeting getMeetingByPersonCode(Integer code);
	
	Meeting getMeetingByPersonInfo(String name, String surname, Integer code);

}
