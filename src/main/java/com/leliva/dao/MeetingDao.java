package com.leliva.dao;

import java.util.Date;
import java.util.List;

import com.leliva.model.Doctor;
import com.leliva.model.Meeting;

public interface MeetingDao {

	Meeting saveMeeting(Meeting meeting);
	
	List<Meeting> getMeetingByPersonInfo(String name, String surname, String code);
	
	Meeting getMeeting(String name, String surname, String code, Doctor doc, Date startDate, Date endDate);
	
	List<Meeting> getMeetingsByDoctor(Long docId, Date date);

	void cancelMeeting(Long meetingId);

	List<Date> getAvailableTimesByDoctor(Long doctorId, Date date);
	
}
