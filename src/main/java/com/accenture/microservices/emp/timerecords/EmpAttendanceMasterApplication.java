package com.accenture.microservices.emp.timerecords;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = { "com.accenture.microservices.emp" })
@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients
//@SpringBootApplication(scanBasePackages = { "com.accenture.microservices.emp.data"})
public class EmpAttendanceMasterApplication {
	
	private static final Logger log = LoggerFactory.getLogger(EmpAttendanceMasterApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EmpAttendanceMasterApplication.class, args);
	}
}
