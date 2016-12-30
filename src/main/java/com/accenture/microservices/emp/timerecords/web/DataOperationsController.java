package com.accenture.microservices.emp.timerecords.web;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.microservices.emp.data.User;
import com.accenture.microservices.emp.data.service.MyService;

@SpringBootApplication
@RestController
public class DataOperationsController {
	public static final Logger log = LoggerFactory.getLogger(DataOperationsController.class);
	
	@Autowired
	MyService myService;
	

	@RequestMapping("/user/{employeeName}")
	public Collection<User> getUserDetails(@PathVariable ("employeeName") String empName){
		Collection<User> userRetrived = this.myService.getUserDetails(empName);
		log.info("started flow: "+empName);
		return userRetrived;
	}
	
}
