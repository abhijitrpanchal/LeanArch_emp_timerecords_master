package com.accenture.microservices.emp.timerecords.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.microservices.emp.timerecords.client.vo.EmployeeDetails;


@FeignClient(name = "empdetailsmaster")
public interface EmployeeDetailsService {
	
	@RequestMapping(value = "/employees/{empId}",method = RequestMethod.GET)
	public EmployeeDetails getEmployeeDetails(@PathVariable ("empId") long id);
	
	@RequestMapping(value = "/employees",method = RequestMethod.GET)
	public List<EmployeeDetails> getAllEmployees();
	

}
