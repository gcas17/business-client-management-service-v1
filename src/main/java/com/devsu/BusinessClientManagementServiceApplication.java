package com.devsu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class BusinessClientManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusinessClientManagementServiceApplication.class, args);
	}

}

