package com.fisemployeeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan("com.fisemployeeservice.model")
@ComponentScan(basePackages = {"com.fisemployeeservice","com.fisemployeeservice.service", "com.fisemployeeservice.repository"})
@EnableJpaRepositories(basePackages = "com.fisemployeeservice.repository")
public class FisEmployeeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FisEmployeeServiceApplication.class, args);
	}

}
