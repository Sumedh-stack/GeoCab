package com.project.UberReviewService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class QuickRideReviewServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuickRideReviewServiceApplication.class, args);
	}

}
