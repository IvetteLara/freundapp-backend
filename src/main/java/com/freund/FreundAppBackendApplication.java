package com.freund;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication
@PropertySource("classpath:messages.properties")
public class FreundAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreundAppBackendApplication.class, args);
	}

}
