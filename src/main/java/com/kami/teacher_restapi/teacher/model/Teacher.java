package com.kami.teacher_restapi.teacher.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name="teacher_tbl")
public class Teacher {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int Id;
	 @Column(name = "fullname")
	 private String fullName;
	 @Column(name = "email_address")
	 private String email;
	 @Column(name = "course")
	 private String course;

	 
	 
	public Teacher(int id, String fName, String email, String course) {
		super();
		Id = id;
		this.fullName = fName;
		this.email = email;
		this.course = course;
	}
	
	 public Teacher() {
		 
	 }
	 
	 public int getId() {
			return Id;
		}
		 
	public void setId(int id) {
		Id = id;
	}
	 public String getfullName() {
		return fullName;
	}
	public void setfullName(String fName) {
		this.fullName = fName;
	}



	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	@Override
	public String toString() {
		return "Teacher [Id=" + Id + ", email=" + email + ", course=" + course + ", fullName=" + fullName + "]";
	}
	
	
	
	 
}
