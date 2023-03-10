package com.kami.teacher_restapi.teacher.error;



import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TeacherErrorHandler {

	@ExceptionHandler(TeacherException.class)
	public ResponseEntity<ErrorType> handleNotFound(TeacherException te){
		  // needs to be implemented
  	     return null;
	}
}
