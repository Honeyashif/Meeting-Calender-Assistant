package com.jsp.springboot.meeting_calender_assistant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.springboot.meeting_calender_assistant.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer>{

}
