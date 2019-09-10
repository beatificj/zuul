package com.sktelecom.auth.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

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
	
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public class UnauthorizedException extends RuntimeException {}
	
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public class ForbiddenException extends RuntimeException {}
}
