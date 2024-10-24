package com.jsp.springboot.meeting_calender_assistant.exception;

public class EmployeeNotFoundByIdException extends RuntimeException{
	private String message;

	public EmployeeNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
