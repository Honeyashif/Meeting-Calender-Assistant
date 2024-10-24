package com.jsp.springboot.meeting_calender_assistant.utility;

public class ResponseStructure<R> {

	private int statusCode;
	private String message;
	private R data;
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public R getData() {
		return data;
	}
	public void setData(R data) {
		this.data = data;
	}
}
