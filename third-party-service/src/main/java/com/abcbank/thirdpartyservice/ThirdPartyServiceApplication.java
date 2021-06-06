package com.abcbank.thirdpartyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;

@SpringBootApplication
public class ThirdPartyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThirdPartyServiceApplication.class, args);
	}

}
