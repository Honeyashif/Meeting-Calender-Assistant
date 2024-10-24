package com.jsp.springboot.meeting_calender_assistant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.springboot.meeting_calender_assistant.entity.Employee;
import com.jsp.springboot.meeting_calender_assistant.entity.Meeting;

public interface MeetingRepository extends JpaRepository<Meeting, Integer>{

//	public List<Meeting> findByE(Employee owner);
}
