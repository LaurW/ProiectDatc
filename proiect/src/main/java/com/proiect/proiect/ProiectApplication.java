package com.proiect.proiect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ProiectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProiectApplication.class, args);
	}

}
