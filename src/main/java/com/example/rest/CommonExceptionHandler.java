package com.example.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.rest.controller.ResourceNotFoundException;
import com.example.rest.dto.Error;

@RestControllerAdvice
public class CommonExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public com.example.rest.dto.Error resourceNotFoundExceptionHandler(ResourceNotFoundException exception) {
		return new Error(HttpStatus.NOT_FOUND, exception.getMessage());
	}

}
