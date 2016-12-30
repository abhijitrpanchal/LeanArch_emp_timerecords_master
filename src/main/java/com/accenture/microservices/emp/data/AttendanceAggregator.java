/**
 * 
 */
package com.accenture.microservices.emp.data;


import java.util.ArrayList;
import java.util.Collection;

import javax.sound.midi.MidiDevice.Info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.accenture.microservices.emp.data.service.AttendanceService;
import com.accenture.microservices.emp.data.service.validation.DocumentValidationServiceUtil;
import com.accenture.microservices.emp.timerecords.web.utility.DocumentValidationUtil;


/**
 * @author j.venugopalan
 *
 */

@SpringBootApplication
public class AttendanceAggregator {
	public static final Logger log = LoggerFactory.getLogger(AttendanceAggregator.class);
	
	@Bean
	DayAttendance dayAttendance(){
		return new DayAttendance();
	}
	@Autowired
	DayAttendance dayAttendance;
	
	@Autowired
	AttendanceService attendanceService;
	
	@Autowired
	DocumentValidationServiceUtil documentValidationServiceUtil;
	
	@Autowired
	DocumentValidationUtil documentValidationUtil;
	
	public Collection<EmployeeAttendance> getEmployeeAttendance(Integer empId){
		Collection<EmployeeAttendance> employeeAttance = attendanceService.getEmployeeAttendance(empId);
		log.info("EmployeeAttenance: "+employeeAttance.toString());
		return employeeAttance;
	}
	
	public Collection<EmployeeAttendance> getAllEmployeesAttendance(){
		Collection<EmployeeAttendance> employeesAttance = attendanceService.getEmployeesAttendance();
		return employeesAttance;
	}

	public Boolean submitEmployeeAttenanceDomain(EmployeeAttendance employeeAttendance){
		Integer employeeId = employeeAttendance.getEmployeeId();
		Boolean submitStatus = false;
		Boolean hoursValidationStatus  = false;
		Collection<DayAttendance> dayAttendance = employeeAttendance.getDayAttendance();
		Boolean dateFormatValicaiton = true;
		dateFormatValicaiton = this.documentValidationUtil.validateDateString(dayAttendance);
		hoursValidationStatus = this.documentValidationUtil.validateHours(dayAttendance);
		log.info("hours validation status: "+hoursValidationStatus);
		if(dateFormatValicaiton && hoursValidationStatus){
			submitStatus = attendanceService.submitEmployeeAttendance(employeeId, dayAttendance);
		}else{
			submitStatus = false;
		}
		
		//this is for comparing the existing and submitted dayattendnace
		//START
		this.documentValidationServiceUtil.compareArrayList();
		
		//END
		//log.info("AttendanceAggregator::: EmployeeId : dayAttendance: Status:  "+employeeId+" :: "+dayAttendance.toString()+" :: "+submitStatus);
		return submitStatus;
	}
	
	public Boolean updateEmployeeAttendance(EmployeeAttendance employeeAttendance){
		Integer employeeId = employeeAttendance.getEmployeeId();
		Collection<DayAttendance> dayAttendance = new ArrayList<DayAttendance>();
		Collection<EmployeeAttendance> employeeAttance = attendanceService.getEmployeeAttendance(employeeId);
		/**
		 * TODO
		 * Logic to update the record
		 */
		return null;
	}
	
	public Boolean deleteEmployeeAttenance(EmployeeAttendance employeeAttendance){
		employeeAttendance.setId(employeeAttendance.getEmployeeId());
		log.info("Request Aggregator: "+employeeAttendance.toString());
		attendanceService.deleteEmployeeAttendance(employeeAttendance);
		return null;
	}
}
