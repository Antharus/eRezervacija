package com.leliva.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	
	public static String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	
	public static String DATE_FORMAT_Y_M_D = "yyyy-MM-dd";
	
	public static String DATE_FORMAT_M_D_Y = "MM/dd/yyyy";
	
	public static String convertToWebString(Date d){
		return new SimpleDateFormat(DATE_FORMAT).format(d);
	}
	
	public static Date convertToDBDate(String date){
		return convertToDate(date,DATE_FORMAT_Y_M_D);
	}
	
	public static Date convertToDate(String date, String format){
		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date yesterday(Date date){
		Date yesterday = org.apache.commons.lang3.time.DateUtils.setHours(date, 0);
		yesterday = org.apache.commons.lang3.time.DateUtils.setMinutes(yesterday, 0);
		return yesterday;
	}
	
	public static Date tomorrow(Date date){
		Date tomorrow = org.apache.commons.lang3.time.DateUtils.addDays(date,1);
		tomorrow = org.apache.commons.lang3.time.DateUtils.addSeconds(tomorrow,-1);
		return tomorrow;
	}
	
	public static Date lastWeek(Date date){
		Date lastWeek = org.apache.commons.lang3.time.DateUtils.setHours(date, 0);
		lastWeek = org.apache.commons.lang3.time.DateUtils.setMinutes(lastWeek, 0);
		lastWeek = org.apache.commons.lang3.time.DateUtils.addDays(lastWeek,-3);
		return lastWeek;
	}
	
	public static Date nextWeek(Date date){
		Date nextWeek = org.apache.commons.lang3.time.DateUtils.addDays(date,1);
		nextWeek = org.apache.commons.lang3.time.DateUtils.addSeconds(nextWeek,-1);
		nextWeek = org.apache.commons.lang3.time.DateUtils.addDays(nextWeek,4);
		return nextWeek;
	}
	

	

}
