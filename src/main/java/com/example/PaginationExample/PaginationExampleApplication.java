package com.example.PaginationExample;

import com.example.PaginationExample.controllers.MoviesController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PaginationExampleApplication {
	private static final Logger logger = LogManager.getLogger(PaginationExampleApplication.class);

	public static void main(String[] args) {
		logger.debug("Main");
		SpringApplication.run(PaginationExampleApplication.class, args);
	}

}
