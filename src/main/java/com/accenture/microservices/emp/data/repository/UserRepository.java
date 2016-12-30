/**
 * 
 */
package com.accenture.microservices.emp.data.repository;

import java.util.List;

import org.springframework.data.couchbase.core.query.View;
import org.springframework.data.repository.CrudRepository;

import com.accenture.microservices.emp.data.User;

/**
 * @author j.venugopalan
 *
 */
public interface UserRepository extends CrudRepository<User, String> {

  
    @View(designDocument = "byName", viewName = "byName")
    List<User> findByLastname(String lastname);
    
  }
