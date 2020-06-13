package com.example.SpringSecurityDemo.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringSecurityDemo.security.config.IsUserAuthority;

@RestController
public class HelloController {

	@Value("${test.encry.value}")
	String value;

	/**
	 * 
	 * To test encrypted values
	 */
	@PostConstruct
	public void afterInit() {
		System.out.println("---------------------------------------------------");
		System.out.println("Value " + value);
		System.out.println("---------------------------------------------------");
	}

	@GetMapping("/hello")
	@PreAuthorize("hasRole('USER')")
	public String sayHello() {
		return value;

	}
	
	@GetMapping("/morning")
	@IsUserAuthority
	public String sayMorning() {
		return "Good morning";

	}

}
