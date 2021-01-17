package com.leliva.model.helpers;

public enum DoctorOccupation {
	
	DERMATOLOGIST("Dermatologas"),
	CARDIOLOGIST("Kardiologas"),
	SURGEON("Chirurgas");
	
	public final String name;
	
	private DoctorOccupation(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}

}
