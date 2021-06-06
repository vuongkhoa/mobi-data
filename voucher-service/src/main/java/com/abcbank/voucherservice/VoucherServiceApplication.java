package com.abcbank.voucherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class VoucherServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoucherServiceApplication.class, args);
	}

}
