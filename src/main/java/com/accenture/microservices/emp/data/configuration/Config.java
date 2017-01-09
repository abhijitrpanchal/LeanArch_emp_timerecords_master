/**
 * 
 */
package com.accenture.microservices.emp.data.configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

/**
 * @author j.venugopalan
 *
 */
@Configuration
@EnableCouchbaseRepositories
public class Config extends AbstractCouchbaseConfiguration {
	
	@Value("${couchbaseDB.url}")
	private String couchbaseDBUrl; 
	
    @Override
    protected List<String> getBootstrapHosts() {
    	return Arrays.asList(couchbaseDBUrl, couchbaseDBUrl);
       
    }

    @Override
    protected String getBucketName() {
        return "attendance";
    }

    @Override
    protected String getBucketPassword() {
        return "ACC@123*";
    }
}
