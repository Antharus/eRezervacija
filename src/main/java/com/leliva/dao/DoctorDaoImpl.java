package com.leliva.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.leliva.model.Doctor;
import com.leliva.model.helpers.DoctorWorkingState;

@Service
public class DoctorDaoImpl extends AbstractGenericDao<Doctor, Long> implements DoctorDao {

	private static final String SELECT_ACTIVE_DOCTORS = "SELECT " //
			+ "doc " //
			+ "FROM " //
			+ "Doctor doc " //
			+ "WHERE "//
			+ "state='" + DoctorWorkingState.ACTIVE + "'";
	
	private static final String SELECT_DOCTOR_BY_ID = "SELECT doc FROM Doctor doc WHERE doc.id=:id";

	@SuppressWarnings("unchecked")
	@Override
	public List<Doctor> getAllDoctors() {
		return getEntityManager().createQuery(SELECT_ACTIVE_DOCTORS).getResultList();
	}

	@Override
	public Doctor getDoctorById(Long id) {
		Query query = getEntityManager().createQuery(SELECT_DOCTOR_BY_ID);
		query.setParameter("id", id);
		Doctor d = getSingleResultOrNull(query);
		return d;
	}

}
