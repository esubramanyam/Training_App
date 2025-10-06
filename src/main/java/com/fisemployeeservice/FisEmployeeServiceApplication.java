package com.fisemployeeservice;

import javax.sql.DataSource;

import org.jobrunr.configuration.JobRunr;
import org.jobrunr.configuration.JobRunrConfiguration;
import org.jobrunr.scheduling.BackgroundJob;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.scheduling.cron.Cron;
import org.jobrunr.storage.sql.common.DefaultSqlStorageProvider;
import org.jobrunr.storage.sql.mysql.MySqlStorageProvider;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.fisemployeeservice.fileSevice.FileServiceImpl;


@SpringBootApplication
@EntityScan("com.fisemployeeservice.model")
@ComponentScan(basePackages = {"com.fisemployeeservice","com.fisemployeeservice.service", "com.fisemployeeservice.repository","com.fisemployeeservice.jobFactory"})
@EnableJpaRepositories(basePackages = "com.fisemployeeservice.repository")
public class FisEmployeeServiceApplication {

	//public final DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(FisEmployeeServiceApplication.class, args);
		
	}
	
//	@Bean
//	ApplicationRunner getFirstJob() {
//		return x -> {
//			BackgroundJob.enqueue(()->System.out.println("First job"));
//		};
//	}
	
	//Programmatic Scheduling
	
//	@Bean
//	CommandLineRunner scheduleTime(JobScheduler scheduler, FileServiceImpl service) {
//		return x -> scheduler.scheduleRecurrently("in-bound-file", Cron.every30seconds(), ()-> service.process());
//	}
//	

}
