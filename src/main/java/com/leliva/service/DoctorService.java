package com.leliva.service;

import java.util.List;

import com.leliva.model.Doctor;

public interface DoctorService {
	
	List<Doctor> getAllDoctors();
	
	Doctor getDoctorById(Long id);

}
