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
import com.accenture.microservices.emp.timerecords.service.TimeRecordsService;
import com.accenture.microservices.emp.timerecords.web.clients.ChargeCodeService;
import com.accenture.microservices.emp.timerecords.web.clients.vo.ChargeCode;
import com.accenture.microservices.emp.timerecords.web.clients.vo.EmployeeAssignments;


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
	
	@Autowired
	TimeRecordsService timeRecordsService;
	
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
	
	@RequestMapping(value = "/timerecords/chargecodes/{wbs}", method = RequestMethod.GET)
	public ChargeCode getChargeCodeDetails(@PathVariable("wbs") String wbs){
		
		log.info("request received: "+"/timerecords/chargecodes/"+wbs);
		ChargeCode chargeCode=timeRecordsService.getChargeCodeDetails(wbs);
		
		return	chargeCode;
	}
	
	@RequestMapping(value = "/timerecords/chargecodes/{wbs}/employees/{empid}", method = RequestMethod.GET)
	public ChargeCode getChargeCodeDetailsOfAnEmployee(@PathVariable("wbs") String wbs,@PathVariable("empid") Integer empid){
		
		log.info("request received: "+"/timerecords/chargecodes/"+wbs+"/employees/"+empid);		
		ChargeCode chargeCode=timeRecordsService.getChargeCodeDetailsOfAnEmployee(wbs, empid);
		
		return	chargeCode;
	}
	
	@RequestMapping(value = "/timerecords/employees/assignments/{empId}", method=RequestMethod.GET)
	public EmployeeAssignments getEmployeeAssignment(@PathVariable("empId") Integer empId){
		
		log.info("request received: "+"/timerecords/employees/assignments/"+empId);
		
		EmployeeAssignments employeeAssignments=null;
		try{
			employeeAssignments=timeRecordsService.getEmployeeAssignment(empId);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return employeeAssignments;
		
	}

}
