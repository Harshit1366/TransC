package com.transc.model;

public class Courses {

	String code;
	String name;
	String ltp;
	String credits;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLtp() {
		return ltp;
	}
	public void setLtp(String ltp) {
		this.ltp = ltp;
	}
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}
	@Override
	public String toString() {
		return "Courses [code=" + code + ", name=" + name + ", ltp=" + ltp + ", credits=" + credits + "]";
	}
	
	
	
}
