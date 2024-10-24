package com.jsp.springboot.meeting_calender_assistant.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.springboot.meeting_calender_assistant.entity.Employee;
import com.jsp.springboot.meeting_calender_assistant.entity.Meeting;
import com.jsp.springboot.meeting_calender_assistant.exception.EmployeeNotFoundByIdException;
import com.jsp.springboot.meeting_calender_assistant.repository.EmployeeRepository;
import com.jsp.springboot.meeting_calender_assistant.repository.MeetingRepository;
import com.jsp.springboot.meeting_calender_assistant.service.MeetingService;
import com.jsp.springboot.meeting_calender_assistant.utility.ResponseStructure;

@Service
public class MeetingServiceImpl implements MeetingService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private MeetingRepository meetingRepository;

	@Override
	public ResponseEntity<ResponseStructure<Meeting>> bookMeeting(Meeting request,Employee employee2) {
		Employee employee=employeeRepository.findById(employee2.getId()).orElseThrow(()->
		                                   new EmployeeNotFoundByIdException("Employee is Not Present for that Specific Id"));
		request.setEmployee(employee);
		Meeting meeting=meetingRepository.save(request);
		
		ResponseStructure<Meeting> responseStructure=new ResponseStructure<Meeting>();
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setData(meeting);
		responseStructure.setMessage("Meeting Booked Succesufully");
		
		return new ResponseEntity<ResponseStructure<Meeting>>(responseStructure, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Meeting>>> findFreeSlots(int employee1Id, int employee2Id,
			int duration) {
		Employee employee1=employeeRepository.findById(employee1Id).orElseThrow(()->
		                                           new EmployeeNotFoundByIdException("Employee Not found By Id"));
		Employee employee2=employeeRepository.findById(employee2Id).orElseThrow(()->
		                                           new EmployeeNotFoundByIdException("Employee Not Found By Id"));
		
		List<Meeting> employee1Meetings=employee1.getMeetings();
		List<Meeting> employee2Meetings=employee2.getMeetings();
		
		
		List<Meeting> freeSlots=new ArrayList<Meeting>();
		
		LocalDateTime startTime=LocalDateTime.now();
		LocalDateTime endTime=startTime.plusDays(1);
		
		freeSlots=findFreeTimeSolts(employee1Meetings,employee2Meetings ,startTime,endTime,duration);
		
		
		ResponseStructure<List<Meeting>> responseStructure=new ResponseStructure<List<Meeting>>();
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setData(freeSlots);
		responseStructure.setMessage("Available Free Slots are as Below");
		
		return new ResponseEntity<ResponseStructure<List<Meeting>>>(responseStructure, HttpStatus.FOUND);
	}

	private List<Meeting> findFreeTimeSolts(List<Meeting> employee1Meetings, List<Meeting> employee2Meetings,
			LocalDateTime startTime, LocalDateTime endTime, int duration) {
		
		List<Meeting> availableSolts=new ArrayList<Meeting>();
		employee1Meetings.sort((m1,m2)->
		              m1.getStartTime().compareTo(m2.getStartTime())
				);
		
		employee2Meetings.sort((m1,m2)->
		                  m1.getStartTime().compareTo(m2.getStartTime())
				);
		
		LocalDateTime lastEndTime=startTime;
		for(Meeting meeting1:employee1Meetings) {
			if(lastEndTime.isBefore(meeting1.getStartTime())&& minutesBetween(lastEndTime,meeting1.getStartTime())>=duration) {
				Meeting freeslots=new Meeting(null,lastEndTime,lastEndTime.plusMinutes(duration),new ArrayList<>());
			}
		}
		
		return availableSolts;
	}
	
	private long minutesBetween(LocalDateTime start,LocalDateTime end) {
		return java.time.Duration.between(start, end).toMinutes();
	}

	@Override
	public ResponseEntity<ResponseStructure<List<String>>> checkConflicts(Meeting meeting) {
		
		return null;
	}

}
