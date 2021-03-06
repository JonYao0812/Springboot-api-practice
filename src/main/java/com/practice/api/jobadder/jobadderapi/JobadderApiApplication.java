package com.practice.api.jobadder.jobadderapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class JobadderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobadderApiApplication.class, args);
	}

}