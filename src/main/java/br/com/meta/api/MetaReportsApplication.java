package br.com.meta.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class MetaReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MetaReportsApplication.class, args);
	}

}
