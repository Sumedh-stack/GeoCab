package com.QuickRide.EntityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EntityServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(EntityServiceApplication.class, args);
	}
}
