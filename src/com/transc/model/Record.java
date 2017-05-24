package com.transc.model;

import java.util.Arrays;

public class Record {
	
	String roll_number;
	String name;
	public Semester[] semesters = new Semester[8];
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getRoll_number() {
		return roll_number;
	}

	public void setRoll_number(String roll_number) {
		this.roll_number = roll_number;
	}

	public Semester[] getSemesters() {
		return semesters;
	}

	public void setSemesters(Semester[] semesters) {
		this.semesters = semesters;
	}

	

	@Override
	public String toString() {
		return "Record [roll_number=" + roll_number + ", name=" + name + ", semesters=" + Arrays.toString(semesters)
				+ ", getName()=" + getName() + ", getRoll_number()=" + getRoll_number() + ", getSemesters()="
				+ Arrays.toString(getSemesters()) + ", hashCode()=" + hashCode() + ", getClass()=" + getClass()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

	
}
