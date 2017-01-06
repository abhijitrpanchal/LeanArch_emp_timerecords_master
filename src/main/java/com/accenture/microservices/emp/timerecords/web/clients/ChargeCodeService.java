package com.accenture.microservices.emp.timerecords.web.clients;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.accenture.microservices.emp.timerecords.web.clients.vo.ChargeCode;

@FeignClient(name = "chargecodes")
public interface ChargeCodeService {
	
	@RequestMapping(value = "/chargecodes/{wbs}",  method = RequestMethod.GET)
	ChargeCode  getChargeCodeDetails(@PathVariable("wbs") String wbs);
	
	@RequestMapping(value = "/chargecodes/{wbs}/employees/{empid}",  method = RequestMethod.GET)
    ChargeCode  getChargeCodeDetailsOfAnEmployee(@PathVariable("wbs") String wbs,@PathVariable("empid") Integer empid);

}
