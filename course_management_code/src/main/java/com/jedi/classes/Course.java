package com.jedi.classes;

public class Course {
	
	private String courseId;
	private String name;
	private int seats;
	private int fee;
		
	public Course() {};
	
	public Course(String courseId, String name,int seats,int fee) {
		super();
		this.courseId = courseId;
		this.name=name;
		this.seats=seats;
		this.fee=fee;
	}


	public String getCourseId() {
		return courseId;
	}


	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	

	public int getSeats() {
		return seats;
	}


	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public int getFee() {
		return fee;
	}


	public void setFee(int fee) {
		this.fee = fee;
	}
	
}