package com.donHub.donHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class DonHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(DonHubApplication.class, args);
	}

}
