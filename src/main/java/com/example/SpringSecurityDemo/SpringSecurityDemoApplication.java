package com.example.SpringSecurityDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources({
		// @PropertySource(value = "file:${ENV_HOME}/config/services/test.properties")
		@PropertySource(value = "file:./src/main/resources/custom.properties") })
public class SpringSecurityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoApplication.class, args);
	}
	
	

}
