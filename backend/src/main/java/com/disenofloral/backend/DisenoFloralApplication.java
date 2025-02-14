package com.disenofloral.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class DisenoFloralApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisenoFloralApplication.class, args);
	}

}
