/**
 * 
 */
package com.accenture.microservices.emp.business;

import java.util.Arrays;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.accenture.microservices.emp.data.AttendanceAggregator;
import com.accenture.microservices.emp.data.EmployeeAttendance;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * @author j.venugopalan
 *
 */

@SpringBootApplication
@Component
@Service
public class AttendanceCalculator {
	public static final Logger log = LoggerFactory.getLogger(AttendanceCalculator.class);
	
	/*@Bean
	AttendanceAggregator attendanceAggregat(){
		return new AttendanceAggregator();
	}*/
	@Autowired
	AttendanceAggregator attendanceAggregator;

	@HystrixCommand(fallbackMethod="handleGetCalculateAttendanceEmployee")
	public Collection<EmployeeAttendance> getCalculateAttendanceEmployee(Integer empId){
		Collection<EmployeeAttendance>  employeeAttenance = this.attendanceAggregator.getEmployeeAttendance(empId);
		return  employeeAttenance;
	}
	
	public Collection<EmployeeAttendance> handleGetCalculateAttendanceEmployee(Integer empId){

		return  Arrays.asList(new EmployeeAttendance());
	}
	
	@HystrixCommand(fallbackMethod="handleGetCalculateAttendanceEmployees")
	public Collection<EmployeeAttendance> getCalculateAttendanceEmployees(){
		Collection<EmployeeAttendance>  employeesAttenance = this.attendanceAggregator.getAllEmployeesAttendance();
		return  employeesAttenance;
	}
	

	public Collection<EmployeeAttendance> handleGetCalculateAttendanceEmployees(){
		
		return  Arrays.asList(new EmployeeAttendance());
	}
	public Boolean submitEmployeeAttendanceWeb(EmployeeAttendance employeeAttendance){
		Boolean status = this.attendanceAggregator.submitEmployeeAttenanceDomain(employeeAttendance);
		return status;
	}
	public Boolean deleteEmployeeAttandance(EmployeeAttendance employeeAttendance){
		this.attendanceAggregator.deleteEmployeeAttenance(employeeAttendance);
		return null;
	}
	

}
