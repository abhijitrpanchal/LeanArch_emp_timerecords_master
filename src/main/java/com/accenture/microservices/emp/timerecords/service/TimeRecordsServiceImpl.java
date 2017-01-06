package com.accenture.microservices.emp.timerecords.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.accenture.microservices.emp.timerecords.web.clients.AssignmentsService;
import com.accenture.microservices.emp.timerecords.web.clients.ChargeCodeService;
import com.accenture.microservices.emp.timerecords.web.clients.vo.ChargeCode;
import com.accenture.microservices.emp.timerecords.web.clients.vo.EmployeeAssignments;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Component
public class TimeRecordsServiceImpl implements TimeRecordsService {
	
	@Autowired
	private ChargeCodeService chargeCodeService;
	
	@Autowired
	private AssignmentsService assignmentsService;

	@Override
	@HystrixCommand(fallbackMethod="handleGetChargeCodeDetails")
	public ChargeCode getChargeCodeDetails(String wbs) {
		
		ChargeCode chargeCode=chargeCodeService.getChargeCodeDetails(wbs);
		
		return	chargeCode;
	}
	
	/*
	 *  hystrix circuitbreaker fallbackMethod for getChargeCodeDetails
	 */
	
	public ChargeCode handleGetChargeCodeDetails(String wbs) {
		
		ChargeCode chargeCode=new ChargeCode();
		
		return	chargeCode;
	}

	@Override
	@HystrixCommand(fallbackMethod="handleGetChargeCodeDetailsOfAnEmployee")
	public ChargeCode getChargeCodeDetailsOfAnEmployee(String wbs, Integer empid) {
		
		ChargeCode chargeCode=chargeCodeService.getChargeCodeDetailsOfAnEmployee(wbs, empid);
		
		return	chargeCode;
	}
	
	/*
	 *  hystrix circuitbreaker fallbackMethod for getChargeCodeDetailsOfAnEmployee
	 */
	public ChargeCode handleGetChargeCodeDetailsOfAnEmployee(String wbs, Integer empid) {
		
		ChargeCode chargeCode=chargeCodeService.getChargeCodeDetailsOfAnEmployee(wbs, empid);
		
		return	chargeCode;
	}
	
	@Override
	@HystrixCommand(fallbackMethod="handleGetEmployeeAssignment")
	public EmployeeAssignments getEmployeeAssignment(Integer id){
		
		EmployeeAssignments employeeAssignments=assignmentsService.getEmployeeAssignment(id);
		
		return employeeAssignments;
		
	}
	
	/*
	 *  hystrix circuitbreaker fallbackMethod for handleGetEmployeeAssignment
	 */
	
	public EmployeeAssignments handleGetEmployeeAssignment(Integer id){
		
		EmployeeAssignments employeeAssignments=new EmployeeAssignments();
		
		return employeeAssignments;
		
	}

}
