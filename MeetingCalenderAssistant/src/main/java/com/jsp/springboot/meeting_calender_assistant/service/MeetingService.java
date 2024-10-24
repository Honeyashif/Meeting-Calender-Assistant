package com.jsp.springboot.meeting_calender_assistant.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.jsp.springboot.meeting_calender_assistant.entity.Employee;
import com.jsp.springboot.meeting_calender_assistant.entity.Meeting;
import com.jsp.springboot.meeting_calender_assistant.utility.ResponseStructure;

public interface MeetingService {

	ResponseEntity<ResponseStructure<Meeting>> bookMeeting(Meeting request,Employee employee);
	
	ResponseEntity<ResponseStructure<List<Meeting>>> findFreeSlots(int employee1Id,int employee2Id,int duration);
	
	ResponseEntity<ResponseStructure<List<String>>> checkConflicts(Meeting meeting);

}
