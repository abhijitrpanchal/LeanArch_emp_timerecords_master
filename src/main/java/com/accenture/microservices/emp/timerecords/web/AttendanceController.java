package com.accenture.microservices.emp.timerecords.web;


import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.microservices.emp.business.AttendanceCalculator;
import com.accenture.microservices.emp.data.EmployeeAttendance;


/**
 * @author j.venugopalan
 *
 */

@SpringBootApplication
@RestController
@Component
@Service
public class AttendanceController {
	
	private static final Logger log = LoggerFactory.getLogger(AttendanceController.class);
	
	/*@Bean
	AttendanceCalculator attendanceCalculator(){
		return new AttendanceCalculator();
	}*/
	
	@Autowired
	AttendanceCalculator attendanceCalculator;
	
	@Autowired
	DataOperationsController dataOperationController;
	
	@RequestMapping(value = "/timerecords/{empId}", method=RequestMethod.GET)
	public Collection<EmployeeAttendance> getEmployeeAttendance(@PathVariable("empId") Integer id){
		Collection<EmployeeAttendance> employeeAttendance = new ArrayList<EmployeeAttendance>();
		if(id != 0 || id != null){
			employeeAttendance = this.attendanceCalculator.getCalculateAttendanceEmployee(id);
		}else{
			employeeAttendance.add(null);
			return employeeAttendance;
		}
		
		log.info("day attendance: "+employeeAttendance.toString());
		return employeeAttendance;
	}
	
	@RequestMapping(value = "/timerecords", method = RequestMethod.GET)
	public Collection<EmployeeAttendance> getEmployeeAttendance(){
		Collection<EmployeeAttendance> employeeAttendance = new ArrayList<EmployeeAttendance>();
		employeeAttendance = this.attendanceCalculator.getCalculateAttendanceEmployees();
		return	employeeAttendance;
	}
	
	@RequestMapping(value = "/employee/attendance/submit", method=RequestMethod.POST)
	public Boolean submitEmployeeAttanceService(@RequestBody Collection<EmployeeAttendance> employeeAttendance){
		EmployeeAttendance employeeAttendance2 = new EmployeeAttendance();
		for(EmployeeAttendance emplAtendance: employeeAttendance){
			employeeAttendance2 = emplAtendance;
		}
		Boolean submitStatus = this.attendanceCalculator.submitEmployeeAttendanceWeb(employeeAttendance2);
		return submitStatus;
	}
	
	@RequestMapping(value = "/employee/attendance/delete", method=RequestMethod.DELETE)
	public Boolean deleteEmployeeAttance(@RequestBody Collection<EmployeeAttendance> employeeAttendance){
		EmployeeAttendance employeeAttendance2 = new EmployeeAttendance();
		for(EmployeeAttendance emplAtendance: employeeAttendance){
			employeeAttendance2 = emplAtendance;
		}
		log.info("request received: "+employeeAttendance2.toString());
		this.attendanceCalculator.deleteEmployeeAttandance(employeeAttendance2);
		return null;
	}

}
