/**
 * 
 */
package com.accenture.microservices.emp.data.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.BooleanLiteral;
import org.springframework.stereotype.Service;

import com.accenture.microservices.emp.data.DayAttendance;
import com.accenture.microservices.emp.data.EmployeeAttendance;
import com.accenture.microservices.emp.data.repository.EmployeeRepository;

/**
 * @author j.venugopalan
 *
 */
@Service
public class AttendanceService {
	public static final Logger log = LoggerFactory.getLogger(AttendanceService.class);

	private final EmployeeRepository employeeRepository;
	
	@Autowired
	public AttendanceService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	/**
	 * @param EmployeeId
	 * @category Service
	 * @Description This method retrieves specific employee's attendance details
	 * @return employeeAttendance
	 */
	public Collection<EmployeeAttendance> getEmployeeAttendance(Integer employeeId){
		log.info("Service:: EmployeeId passed: "+employeeId);
		Collection<EmployeeAttendance> employeeAttendance = employeeRepository.findByEmployeeId(employeeId);
		log.info("Service:: EmployeeAttendance Attance retrived: "+employeeAttendance.toString());
		return employeeAttendance;
	}
	/**
	 * @param No Parameter
	 * @category Service
	 * @Description This method retrieves all employees attendance details
	 * @return employeesAttendance
	 */
	public Collection<EmployeeAttendance> getEmployeesAttendance(){
		Collection<EmployeeAttendance> employeesAttendance = (Collection<EmployeeAttendance>) employeeRepository.findAll();
		log.info("Service:: EmployeeAttendance Attance retrived: "+employeesAttendance.toString());
		return employeesAttendance;
		
	}
	/**
	 * 
	 * @param employeeId, date, hours, chargecode
	 * @param date
	 * @param hours
	 * @param chargeCode
	 * @return
	 */
	public Boolean submitEmployeeAttendance(Integer employeeId, Collection<DayAttendance> dayAttendanceParam){
		EmployeeAttendance employeeAttendance = new EmployeeAttendance();
		log.info("Employee ID: "+employeeId);
		employeeAttendance.setId(employeeId);
		employeeAttendance.setEmployeeId(employeeId);
		
		/**
		 * TODO 
		 * Add document validation
		 */
		Collection<EmployeeAttendance> employeeAttance = getEmployeeAttendance(employeeId);
		Collection<DayAttendance> dayAttendance = new ArrayList<DayAttendance>();
		for(EmployeeAttendance ea : employeeAttance){
			dayAttendance = ea.getDayAttendance();
		}
		Collection<DayAttendance> dayAttConsolidated = new ArrayList<DayAttendance>();
		dayAttConsolidated.addAll(dayAttendance);
		dayAttConsolidated.addAll(dayAttendanceParam);
		employeeAttendance.setDayAttendance(dayAttConsolidated);
		EmployeeAttendance employeeAttenanceSaved = employeeRepository.save(employeeAttendance);
		log.info("employee object for save: "+employeeAttendance.toString());
		log.info("return from save: "+employeeAttenanceSaved.toString());
		Boolean submitString;
		if(employeeAttenanceSaved.toString().equals(employeeAttendance.toString())){
			submitString = true;
		}else{
			submitString = false;
		}
		return submitString;
	}
	
	public Boolean deleteEmployeeAttendance(EmployeeAttendance employeeAttendance){
		employeeRepository.delete(employeeAttendance);
		Boolean deleteStatus = false;		
		return deleteStatus;
	}
}
