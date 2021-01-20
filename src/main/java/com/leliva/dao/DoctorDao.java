package com.leliva.dao;

import java.util.List;

import com.leliva.model.Doctor;

public interface DoctorDao {
	
	List<Doctor> getAllDoctors();

	Doctor getDoctorById(Long id);

}
