package com.transc.model;

import java.util.Arrays;

public class Semester {
	
	int sem_no;
	Courses[] courses;
	Score[] scores;
	double sgpa;
	double cgpa;
	double credits_earned;
	public int getSem_no() {
		return sem_no;
	}
	public void setSem_no(int sem_no) {
		this.sem_no = sem_no;
	}
	public Courses[] getCourses() {
		return courses;
	}
	public void setCourses(Courses[] courses) {
		this.courses = courses;
	}
	public Score[] getScores() {
		return scores;
	}
	public void setScores(Score[] scores) {
		this.scores = scores;
	}
	public double getSgpa() {
		return sgpa;
	}
	public void setSgpa(double sgpa) {
		this.sgpa = sgpa;
	}
	public double getCgpa() {
		return cgpa;
	}
	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}
	public double getCredits_earned() {
		return credits_earned;
	}
	public void setCredits_earned(double credits_earned) {
		this.credits_earned = credits_earned;
	}
	@Override
	public String toString() {
		return "Semester [sem_no=" + sem_no + ", courses=" + Arrays.toString(courses) + ", scores="
				+ Arrays.toString(scores) + ", sgpa=" + sgpa + ", cgpa=" + cgpa + ", credits_earned=" + credits_earned
				+ "]";
	}

	
	
}
