package com.example.FIS_EmployerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FisEmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FisEmployeeServiceApplication.class, args);
	}

}
