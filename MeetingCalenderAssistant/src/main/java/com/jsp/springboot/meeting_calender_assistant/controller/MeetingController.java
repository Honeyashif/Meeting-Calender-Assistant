package com.jsp.springboot.meeting_calender_assistant.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.springboot.meeting_calender_assistant.entity.Employee;
import com.jsp.springboot.meeting_calender_assistant.entity.Meeting;
import com.jsp.springboot.meeting_calender_assistant.service.MeetingService;
import com.jsp.springboot.meeting_calender_assistant.utility.ResponseStructure;

@RestController
public class MeetingController {

	@Autowired
	private MeetingService meetingService;

	@PostMapping("/meeting/book")
	public ResponseEntity<ResponseStructure<Meeting>> bookMeeting(@RequestBody Meeting request,@RequestBody Employee employee){
		return meetingService.bookMeeting(request,employee);
	}

	@GetMapping("/meeting/find-free-slots")
	public ResponseEntity<ResponseStructure<List<Meeting>>> findFreeSlots(int employee1Id, int employee2Id,
			int duration) {
		return meetingService.findFreeSlots(employee1Id, employee2Id, duration);
	}
	@GetMapping("/meeting/check-conflicts")
	public ResponseEntity<ResponseStructure<List<String>>> checkConflicts(Meeting meeting) {

		return meetingService.checkConflicts(meeting);
	}

}
