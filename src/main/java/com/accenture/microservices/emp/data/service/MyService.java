/**
 * 
 */
package com.accenture.microservices.emp.data.service;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accenture.microservices.emp.data.User;
import com.accenture.microservices.emp.data.repository.UserRepository;

/**
 * @author j.venugopalan
 *
 */
@Service
public class MyService {
	public static final Logger log = LoggerFactory.getLogger(MyService.class);

    private final UserRepository userRepository;

    @Autowired
    public MyService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public Collection<User> getUserDetails(String lastName) {
    	log.info("Name passed from UI: "+lastName);
    	
        List<User> userFamily = userRepository.findByLastname(lastName);
        
        log.info("This is list of users: "+userFamily.toString());
        return userFamily;
           
    }
}
