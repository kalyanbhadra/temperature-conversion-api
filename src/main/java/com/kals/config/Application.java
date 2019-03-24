package com.kals.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.kals.controller.TemperatureController;

// same as @Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication 
//As controller is in different package, this must be explicitly mentioned
@ComponentScan(basePackageClasses = TemperatureController.class)
public class Application extends SpringBootServletInitializer{
  
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}