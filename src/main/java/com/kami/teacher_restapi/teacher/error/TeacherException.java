package com.kami.teacher_restapi.teacher.error;


import org.springframework.http.HttpStatus;

public class TeacherException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String error;
	HttpStatus code;
	
	public TeacherException() {
		super();
	}

	public TeacherException(String error, HttpStatus code) {
		this.error = error;
		this.code = code;
	}

	
	
}
