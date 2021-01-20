package com.leliva.service;

import java.util.Date;
import java.util.List;

import com.leliva.model.Doctor;
import com.leliva.model.Meeting;
import com.leliva.model.Person;

public interface MeetingService {
	
	Meeting saveMeeting(Meeting meeting);
	
	List<Meeting> getMeetingsByPersonInfo(String name, String surname, String code);
	
	List<Meeting> getMeetingsByPersonInfo(Person p);
	
	List<Meeting> getMeetingsByDoctor(Long doctorId, Date date);
	
	Meeting getMeeting(String name, String surname, String code, Doctor doc, Date startDate, Date endDate);
	
	Meeting getMeeting(Person p, Doctor doc, Date startDate, Date endDate);

	void cancelMeeting(Long meetingId, Long doctorId);

	List<Date> getAvailableTimesByDoctor(Long doctorId, Date date);

}
