package com.bermanfaat.formbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//Agar saat ditest di postman tidak meminta password//
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class FormbuilderBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormbuilderBackendApplication.class, args);
	}
}
