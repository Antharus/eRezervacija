package com.leliva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leliva.dao.DoctorDao;
import com.leliva.model.Doctor;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	@Autowired
	DoctorDao doctorDao;

	@Override
	public List<Doctor> getAllDoctors() {
		return doctorDao.getAllDoctors();
	}

	@Override
	public Doctor getDoctorById(Long id) {
		return doctorDao.getDoctorById(id);
	}

}
