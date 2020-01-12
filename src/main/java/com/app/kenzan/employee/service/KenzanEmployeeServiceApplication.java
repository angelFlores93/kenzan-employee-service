package com.app.kenzan.employee.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 
 * @author aaflo
 *
 */
@SpringBootApplication
@EntityScan({"com.app.kenzan.employee.commons.models.entity"})
@EnableEurekaClient	
public class KenzanEmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KenzanEmployeeServiceApplication.class, args);
	}

}
