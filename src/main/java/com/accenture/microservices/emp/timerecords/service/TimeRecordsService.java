package com.accenture.microservices.emp.timerecords.service;



import com.accenture.microservices.emp.timerecords.web.clients.vo.ChargeCode;
import com.accenture.microservices.emp.timerecords.web.clients.vo.EmployeeAssignments;

public interface TimeRecordsService {
	
	public ChargeCode getChargeCodeDetails(String wbs);
	
	public ChargeCode getChargeCodeDetailsOfAnEmployee(String wbs,Integer empid);
	
	public EmployeeAssignments getEmployeeAssignment(Integer id);

}
