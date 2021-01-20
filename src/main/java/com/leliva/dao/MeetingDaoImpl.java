package com.leliva.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.leliva.model.Doctor;
import com.leliva.model.Meeting;
import com.leliva.utils.DateUtils;

@Service
public class MeetingDaoImpl extends AbstractGenericDao<Meeting, Long> implements MeetingDao {

	private static final String SELECT_MEETINGS_BY_PERSON_INFO = "SELECT meet FROM Meeting meet WHERE meet.person.name=:name AND meet.person.surname=:surname AND meet.person.personCode=:personCode AND meet.meetingDate BETWEEN :stDate AND :edDate  AND meet.canceled is false ORDER BY meet.id desc";

	private static final String SELECT_MEETING_BY_PERSON_DOCTOR_INFO = "SELECT meet FROM Meeting meet WHERE meet.person.name=:name AND meet.person.surname=:surname AND meet.person.personCode=:personCode AND meet.doctor.id=:doctor AND meet.meetingDate BETWEEN :stDate AND :edDate AND meet.canceled is false ORDER BY meet.id desc";

	private static final String SELECT_MEETING_BY_DOCTOR_DAY = "SELECT meet FROM Meeting meet WHERE meet.doctor.id=:doctor AND meet.meetingDate BETWEEN :stDate AND :edDate AND meet.canceled is false ORDER BY meet.meetingDate";

	private static final String SELECT_DATES_BY_DOCTOR_DAY = "SELECT meet.meetingDate FROM Meeting meet WHERE meet.doctor.id=:docId AND meet.meetingDate BETWEEN :stDate AND :edDate AND meet.canceled is false";

	public Meeting saveMeeting(Meeting meeting) {
		return save(meeting);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Meeting> getMeetingByPersonInfo(String name, String surname, String code) {
		Query query = getEntityManager().createQuery(SELECT_MEETINGS_BY_PERSON_INFO);
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		query.setParameter("personCode", code);
		query.setParameter("stDate", new Date());
		query.setParameter("edDate",  org.apache.commons.lang3.time.DateUtils.addMonths(new Date(), 3));
		return query.getResultList();
	}

	@Override
	public Meeting getMeeting(String name, String surname, String code, Doctor doc, Date startDate, Date endDate) {
		Query query = getEntityManager().createQuery(SELECT_MEETING_BY_PERSON_DOCTOR_INFO);
		query.setParameter("name", name);
		query.setParameter("surname", surname);
		query.setParameter("personCode", code);
		query.setParameter("doctor", doc.getId());
		query.setParameter("stDate", startDate);
		query.setParameter("edDate", endDate);
		return getSingleResultOrNull(query);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Meeting> getMeetingsByDoctor(Long docId, Date date) {
		Query query = getEntityManager().createQuery(SELECT_MEETING_BY_DOCTOR_DAY);
		query.setParameter("doctor", docId);
		query.setParameter("stDate", DateUtils.yesterday(date));
		query.setParameter("edDate", DateUtils.tomorrow(date));
		return query.getResultList();
	}

	@Override
	public void cancelMeeting(Long meetingId) {
		Meeting m = getById(meetingId);
		m.setCanceled(true);
		save(m);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Date> getAvailableTimesByDoctor(Long doctorId, Date date) {
		Query query = getEntityManager().createQuery(SELECT_DATES_BY_DOCTOR_DAY);
		query.setParameter("docId", doctorId);
		query.setParameter("stDate", DateUtils.yesterday(date));
		query.setParameter("edDate", DateUtils.tomorrow(date));
		return query.getResultList();
	}

}
