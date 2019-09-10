package com.sktelecom.auth.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/auth")
@SpringBootApplication
public class AuthDemoApplication {

	@RequestMapping("/sucess")
	public String auth() {
		return "success";
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		throw new UnauthorizedException();
	}
	
	@RequestMapping("/forbidden")
	public String forbidden() {
		throw new ForbiddenException();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AuthDemoApplication.class, args);
	}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public class UnauthorizedException extends RuntimeException {}
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public class ForbiddenException extends RuntimeException {}

}
