package com.transc.model;

public class Score {
	
	
	String roll_no;
	String student_name;
	String course_id;
	String grade;
	
	
	public String getStudent_name() {
		return student_name;
	}
	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}
	public String getRoll_no() {
		return roll_no;
	}
	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "Score [roll_no=" + roll_no + ", student_name=" + student_name + ", course_id=" + course_id + ", grade="
				+ grade + "]";
	}

}
