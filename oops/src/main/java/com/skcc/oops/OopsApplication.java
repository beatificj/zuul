package com.skcc.oops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sktelecom.swing.httpclient.core.EnableSwingClients;

@EnableSwingClients
@SpringBootApplication
public class OopsApplication {

	public static void main(String[] args) {
		SpringApplication.run(OopsApplication.class, args);
	}

}
