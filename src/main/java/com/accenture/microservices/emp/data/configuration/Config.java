/**
 * 
 */
package com.accenture.microservices.emp.data.configuration;

import java.util.Arrays;
import java.util.List;

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

    @Override
    protected List<String> getBootstrapHosts() {
        return Arrays.asList("http://localhost:8091", "http://localhost:8091");
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
