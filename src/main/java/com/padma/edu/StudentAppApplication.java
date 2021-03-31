package com.padma.edu;

import io.swagger.annotations.SwaggerDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = {"com.padma.edu"})
public class StudentAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppApplication.class, args);
	}

}
