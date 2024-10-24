package com.jsp.springboot.meeting_calender_assistant.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.springboot.meeting_calender_assistant.exception.EmployeeNotFoundByIdException;

@RestControllerAdvice
public class ApplicationHandler {
	@ExceptionHandler
	public ResponseEntity<ErrorStructure<String>> employeeNotFoundException(EmployeeNotFoundByIdException ee){
		ErrorStructure<String> errorStructure=new ErrorStructure<String>();
		errorStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		errorStructure.setErrorMessage("Employee Not Found By Id");
		errorStructure.setData(ee.getMessage());
		
		return new ResponseEntity<ErrorStructure<String>>(errorStructure, HttpStatus.NOT_FOUND);
	}
	
}
