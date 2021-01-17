package com.leliva.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.leliva.model.Doctor;
import com.leliva.model.Meeting;
import com.leliva.model.Person;
import com.leliva.service.DoctorService;
import com.leliva.service.MeetingService;

@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@Autowired
	DoctorService doctorService;

	@RequestMapping("/external/rezervation_creation")
	public ModelAndView createRezervation(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "personCode", required = false) String personCode,
			@RequestParam(value = "doctorId", required = false) String doctorId,
			@RequestParam(value = "date", required = false) Date date) {
		if (name != null && surname != null && personCode != null && doctorId != null && date != null) {
			Person p = new Person();
			p.setName(name);
			p.setSurname(surname);
			p.setPersonCode(Integer.parseInt(personCode));
			Meeting m = new Meeting();
			m.setMeetingDate(date);
			m.setPerson(p);
			Doctor d = doctorService.getDoctorById(doctorId.substring(doctorId.indexOf("-")));
			if (d == null) {

			}
			m.setDoctor(d);
			meetingService.saveMeeting(m);
		}
		ModelAndView mav = new ModelAndView("/external/rezervation_creation");
		List<Doctor> doctors = doctorService.getAllDoctors();
		mav.addObject("doctors", doctors);
		return mav;
	}

	@RequestMapping("/external/rezervation_check")
	public ModelAndView checkRezervation() {
		return new ModelAndView("/external/rezervation_check");
	}

	@RequestMapping("/internal/rezervation_list") // TODO: Isskaidyti i
													// skirtingas klases
	public ModelAndView hello4World() {
		return new ModelAndView("/internal/rezervation_list");
	}

	@RequestMapping("/getMeetingsForTheDay")
	public String getDay(@RequestParam("day") String day, @RequestParam("id") String id) {
		System.out.println(day);
		return "";
	}

}