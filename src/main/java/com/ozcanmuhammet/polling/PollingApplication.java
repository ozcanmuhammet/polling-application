package com.ozcanmuhammet.polling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PollingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PollingApplication.class, args);
	}

}
 