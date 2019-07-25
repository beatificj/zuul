package com.skcc.oops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.skcc.oops.kubernetes.EnableOopsClients;


@EnableOopsClients
@SpringBootApplication
public class OopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopsApplication.class, args);
	}

}
