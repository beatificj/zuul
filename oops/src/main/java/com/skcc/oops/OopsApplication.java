package com.skcc.oops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopsApplication.class, args);
	}

}
