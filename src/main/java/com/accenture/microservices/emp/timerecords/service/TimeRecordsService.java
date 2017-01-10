package com.accenture.microservices.emp.timerecords.service;



import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.accenture.microservices.emp.timerecords.client.vo.ChargeCode;
import com.accenture.microservices.emp.timerecords.client.vo.EmployeeAssignments;
import com.accenture.microservices.emp.timerecords.client.vo.EmployeeDetails;

public interface TimeRecordsService {
	
	public ChargeCode getChargeCodeDetails(String wbs);
	
	public ChargeCode getChargeCodeDetailsOfAnEmployee(String wbs,Integer empid);
	
	public EmployeeAssignments getEmployeeAssignment(Integer id);
	
	public EmployeeDetails getEmployeeDetails(long id) ;
	
	public List<EmployeeDetails> getAllEmployees();

}
