package com.leliva.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.leliva.model.Doctor;
import com.leliva.model.Meeting;
import com.leliva.model.Person;
import com.leliva.model.helper.Message;
import com.leliva.model.helper.MessageType;
import com.leliva.service.DoctorService;
import com.leliva.service.MeetingService;
import com.leliva.service.PersonService;
import com.leliva.utils.DateUtils;

/*
 * TODO:
 *  1-Iskelti konstantas
 *  2-Sustvarkyti hibernate queries
 *  3-Isskaidyti Isorinio ir Vidinio portalo controllerius 
 *  
 *  4-Once a week registration per person per doctor
 */
@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@Autowired
	DoctorService doctorService;

	@Autowired
	PersonService personService;
	
	@RequestMapping({"/external/rezervation_creation", "/"})
	public ModelAndView createRezervation(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "personCode", required = false) String personCode,
			@RequestParam(value = "doctorId", required = false) Long doctorId,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm") Date date) {
		ModelAndView mav = new ModelAndView("/external/rezervation_creation");
		Message message = null;
		if (name != null && surname != null && personCode != null && doctorId != null && date != null) {
			try {
				Person p = personService.getPersonByInfo(name, surname, personCode);
				if (p == null) {
					p = new Person();
					p.setName(name);
					p.setSurname(surname);
					p.setPersonCode(personCode);
				}

				Doctor d = doctorService.getDoctorById(doctorId);
				if (d == null) {
					message = new Message("Nurodytas daktaras nerastas, pasome kreiptis i adminsitratoriu",
							MessageType.FAILURE);
				}
				Meeting createdMeeting = meetingService.getMeeting(p, d, DateUtils.lastWeek(date),
						DateUtils.nextWeek(date));

				Meeting m = new Meeting();
				m.setMeetingDate(date);
				m.setPerson(p);

				if (createdMeeting != null && d.equals(createdMeeting.getDoctor())) {
					message = new Message(
							"Jus jau esate uzsiregistrave susitikimui su " + d.getName() + " " + d.getSurname()
									+ ", vizito data: " + DateUtils.convertToWebString(createdMeeting.getMeetingDate()),
							MessageType.FAILURE);
				} else {
					m.setDoctor(d);
					meetingService.saveMeeting(m);
					message = new Message("Susitikimas sekmingai rezervuotas, iki pasimatymo!", MessageType.SUCCESS);
				}
			} catch (Exception e) {
				e.printStackTrace();
				message = new Message("Ivyko klaida, pasome kreiptis i adminsitratoriu", MessageType.FAILURE);
			}

		}
		mav.addObject("message", message);
		List<Doctor> doctors = doctorService.getAllDoctors();
		mav.addObject("doctors", doctors);
		return mav;
	}

	@RequestMapping("/external/rezervation_check")
	public ModelAndView checkRezervation(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "surname", required = false) String surname,
			@RequestParam(value = "personCode", required = false) String personCode) {
		ModelAndView mav = new ModelAndView("/external/rezervation_check");
		Message message = null;
		if (name != null && surname != null && personCode != null) {
			List<Meeting> meetings = meetingService.getMeetingsByPersonInfo(name, surname, personCode);
			if (meetings == null || meetings.size() == 0) {
				message = new Message("Pagal ivestus duomenis rezervaciju 3 menesiu begyje nerasta", MessageType.FAILURE);
			} else {
				StringBuilder sb = new StringBuilder();
				for (Meeting meeting : meetings) {
					sb.append(meeting.getDoctor().getName() + " " + meeting.getDoctor().getSurname() + " jusu laukia: "
							+ DateUtils.convertToWebString(meeting.getMeetingDate()) + "\n");
				}
				message = new Message(sb.toString(), MessageType.SUCCESS);
			}
		}
		mav.addObject("message", message);
		return mav;
	}

	@RequestMapping("/internal/rezervation_list")
	public ModelAndView rezervationList() {
		return new ModelAndView("/internal/rezervation_list");
	}

	@RequestMapping(path = "/internal/getMeetingsForTheDay", produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Meeting> getMeetingsForTheDay(@RequestParam("day") String day,
			@RequestParam("doctorId") Long doctorId) {
		List<Meeting> m = meetingService.getMeetingsByDoctor(doctorId, DateUtils.convertToDBDate(day));
		return m;
	}

	@RequestMapping(path = "/internal/deleteMeetingForDoctor")
	public void deleteMeetingForDoctor(@RequestParam("meetingId") Long meetingId,
			@RequestParam("doctorId") Long doctorId) {
		meetingService.cancelMeeting(meetingId, doctorId);
	}

	@RequestMapping(path = {"/external/getMeetingsForTheDay", "/getMeetingsForTheDay"}, produces = "application/json; charset=UTF-8")
	public @ResponseBody List<Date> getMeetingsForTheDayExternal(@RequestParam("day") String day,
			@RequestParam("doctorId") Long doctorId) {
		List<Date> m = meetingService.getAvailableTimesByDoctor(doctorId,
				DateUtils.convertToDate(day, DateUtils.DATE_FORMAT_M_D_Y));
		return m;
	}

}