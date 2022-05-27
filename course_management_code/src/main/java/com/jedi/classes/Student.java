package com.jedi.classes;

public class Student{

	String userId;
	String name;
	String department;
	
	public Student() {
		
	}
	
    public Student(String userId, String name, String department) {
    	this.userId = userId;
    	this.name = name;
    	this.department = department;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}