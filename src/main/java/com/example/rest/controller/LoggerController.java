package com.example.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(LoggerController.class);

	@GetMapping(path = { "logs" })
	public String LOGGER() {
		LOGGER.trace("This is a TRACE level message");
		LOGGER.debug("This is a DEBUG level message");
		LOGGER.info("This is an INFO level message");
		LOGGER.warn("This is a WARN level message");
		LOGGER.error("This is an ERROR level message");

		return "See console for logs";
	}

}
