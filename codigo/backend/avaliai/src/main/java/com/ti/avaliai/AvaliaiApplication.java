package com.ti.avaliai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication//(exclude = { SecurityAutoConfiguration.class })
public class AvaliaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliaiApplication.class, args);
	}

}
